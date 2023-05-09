package dev.demo.springboot21refresh.bootstrap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomContextInitializerConfig {

    private static CustomApplicationContextInitializer customApplicationContextInitializer;

    @Bean
    public CustomApplicationContextInitializer customApplicationContextInitializer() {
        if(customApplicationContextInitializer == null) {
            customApplicationContextInitializer = new CustomApplicationContextInitializer();
        }
        return customApplicationContextInitializer;

    }

}
