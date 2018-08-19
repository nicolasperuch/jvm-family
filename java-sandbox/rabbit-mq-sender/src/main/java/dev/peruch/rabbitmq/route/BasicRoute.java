package dev.peruch.rabbitmq.route;

import dev.peruch.rabbitmq.model.OrderModel;
import dev.peruch.rabbitmq.service.OrderService;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BasicRoute extends RouteBuilder {

    @Autowired
    private OrderService orderService;

    @Override
    public void configure() throws Exception {
        from("direct:processOrder")
            .routeId("processOrder")
                .process(this::sendEventToRabbitMq)
                .process(this::buildResponse)
            .end();
    }

    private void sendEventToRabbitMq(Exchange exchange) {
        OrderModel order = (OrderModel) exchange.getIn().getBody();
        orderService.send(order);
        exchange.setProperty("orderModel", order);
    }

    private void buildResponse(Exchange exchange) {
        OrderModel order = (OrderModel) exchange.getProperty("orderModel");
        order.setStatus("Approved");
    }
}
