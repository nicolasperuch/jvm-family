package dev.peruch.rabbitxml.service;

import org.apache.camel.ProducerTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitService {

    @Autowired
    private ProducerTemplate producerTemplate;

    @RabbitListener(queues = {"${queue.order.name}"})
    public void receive(Message message) throws Exception {
        producerTemplate.requestBody("direct:startPayment", message);
    }
}
