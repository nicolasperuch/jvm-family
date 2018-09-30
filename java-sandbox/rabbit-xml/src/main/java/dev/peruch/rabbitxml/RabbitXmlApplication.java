package dev.peruch.rabbitxml;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class RabbitXmlApplication {
	public static void main(String[] args) {
		SpringApplication.run(RabbitXmlApplication.class, args);
	}
}
