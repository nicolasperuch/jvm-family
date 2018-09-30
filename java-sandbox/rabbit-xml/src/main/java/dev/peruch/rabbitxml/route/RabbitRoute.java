package dev.peruch.rabbitxml.route;

import dev.peruch.rabbitxml.model.PaymentModel;
import dev.peruch.rabbitxml.route.handler.GeneralErrorHandler;
import dev.peruch.rabbitxml.service.XmlService;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitRoute extends RouteBuilder{

    @Autowired
    private XmlService xmlService;

    @Autowired
    private GeneralErrorHandler generalErrorHandler;

    @Override
    public void configure() throws Exception {
        onException(Exception.class)
                .process(generalErrorHandler)
                .handled(true);

        from("direct:startPayment")
                .routeId("direct:startPayment")
                    .process(this::treatXml)
                    .process(this::requestPayment);
    }

    private void treatXml(Exchange exchange) throws Exception {
        Message message = (Message) exchange.getIn().getBody();
        PaymentModel paymentModel = xmlService.buildPaymentModel(message);
        exchange.setProperty("paymentModel", paymentModel);
    }

    private void requestPayment(Exchange exchange) throws Exception {
        PaymentModel paymentModel = exchange.getProperty("paymentModel", PaymentModel.class);
        System.out.println(paymentModel.toString());
    }
}
