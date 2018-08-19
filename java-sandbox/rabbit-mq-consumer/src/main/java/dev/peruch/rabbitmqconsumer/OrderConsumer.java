package dev.peruch.rabbitmqconsumer;

import dev.peruch.rabbitmqconsumer.service.InfluxDBService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumer {

    @Autowired
    private InfluxDBService influxDB;

    @RabbitListener(queues = {"${queue.order.name}"})
    public void receive(@Payload String order) {
        influxDB.saveEvent(order);
        System.out.println("Order: " + order);
    }
}