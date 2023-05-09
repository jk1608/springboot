package dev.demo.springboot24refresh.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"dev.demo.springboot24refresh.main"})
public class SpringBoot24RefreshApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot24RefreshApplication.class, args);
	}

}
