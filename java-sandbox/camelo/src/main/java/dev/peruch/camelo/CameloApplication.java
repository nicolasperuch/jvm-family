package dev.peruch.camelo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CameloApplication {

	public static void main(String[] args) {
		SpringApplication.run(CameloApplication.class, args);
	}
}
