package dev.peruch.camelo.route;

import dev.peruch.camelo.model.ClientModel;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class BasicRoute extends RouteBuilder{

    @Override
    public void configure() throws Exception {
        from("direct:smallLogic")
            .routeId("smallLogic")
                .process(this::applyLogic)
                .process(this::printValue)
            .end();
    }

    private void applyLogic(Exchange exchange) {
        ClientModel clientModel = (ClientModel) exchange.getIn().getBody();
        clientModel.setName("Danilo");
        clientModel.setSalary("3000.00");
        clientModel.setBonus("%2");
        exchange.setProperty("clientModel", clientModel);
    }

    private void printValue(Exchange exchange) {
        ClientModel clientModel = (ClientModel) exchange.getProperty("clientModel");
        System.out.println(clientModel.toString());
    }
}
