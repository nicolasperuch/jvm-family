package dev.peruch.rabbitxml.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.peruch.rabbitxml.service.XmlService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.regex.Pattern;

import static dev.peruch.rabbitxml.service.XmlService.REGEX_ATTRIBUTES;
import static dev.peruch.rabbitxml.service.XmlService.REGEX_ROOT_XML;

@Configuration
public class Beans {

    @Bean
    public XmlService xmlService(){
        XmlService xmlService = new XmlService();
        xmlService.setObjectMapper(new ObjectMapper());
        xmlService.setPatternAttributes(Pattern.compile(REGEX_ATTRIBUTES));
        xmlService.setPatternRoot(Pattern.compile(REGEX_ROOT_XML));
        return xmlService;
    }
}
