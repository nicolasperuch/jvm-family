package dev.peruch.rabbitmq.controller;

import dev.peruch.rabbitmq.controller.dto.OrderDto;
import dev.peruch.rabbitmq.model.OrderModel;
import dev.peruch.rabbitmq.service.OrderService;
import org.apache.camel.ProducerTemplate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class Controller {

    @Autowired
    private OrderService queueSender;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProducerTemplate producerTemplate;

    @PostMapping("/order")
    public ResponseEntity sendOrder(@RequestBody OrderDto orderDto){

        Object response = Stream.of(orderDto)
                .map(this::convertToModel)
                .map(c -> producerTemplate.requestBody("direct:processOrder", c))
                .map(c -> convertToDto((OrderModel) c))
                .findFirst()
                .get();

        return ok(response);
    }

    private OrderModel convertToModel(OrderDto orderDto) {
        return modelMapper.map(orderDto, OrderModel.class);
    }

    private OrderDto convertToDto(OrderModel orderModel) {
        return modelMapper.map(orderModel, OrderDto.class);
    }
}
