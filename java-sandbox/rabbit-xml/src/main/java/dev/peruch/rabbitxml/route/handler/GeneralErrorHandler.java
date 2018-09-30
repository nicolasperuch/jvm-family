package dev.peruch.rabbitxml.route.handler;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class GeneralErrorHandler implements Processor{

    @Override
    public void process(Exchange exchange) throws Exception {
        //logic here
    }
}
