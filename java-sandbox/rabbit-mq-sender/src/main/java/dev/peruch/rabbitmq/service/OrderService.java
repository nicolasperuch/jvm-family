package dev.peruch.rabbitmq.service;

import dev.peruch.rabbitmq.model.OrderModel;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queue;

    public void send(OrderModel order) {
        rabbitTemplate.convertAndSend(this.queue.getName(), order.toString());
    }
}