package dev.peruch.camelo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.any;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket productApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("dev.peruch.camelo"))
                .paths(any())
                .build()
                .apiInfo(infoApi().build());
    }

    private ApiInfoBuilder infoApi() {

        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();

        apiInfoBuilder.title("Camelo-poc");
        apiInfoBuilder.description("Camel with Spring Boot.");
        apiInfoBuilder.version("1.0");
        apiInfoBuilder.license("Licence - OSS!");
        apiInfoBuilder.contact(contact());

        return apiInfoBuilder;
    }

    private Contact contact() {
        return new Contact(
                "Nicolas Peruch",
                "@nicolasperuch",
                "nicolasperuch.2010@gmail.com");
    }

}
