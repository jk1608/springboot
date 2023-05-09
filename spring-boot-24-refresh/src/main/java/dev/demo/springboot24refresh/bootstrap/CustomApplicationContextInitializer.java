package dev.demo.springboot24refresh.bootstrap;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;

import java.util.ArrayList;
import java.util.List;

@RefreshScope
public class CustomApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext>, Ordered {

    private List<PropertySource<?>> propertySources = new ArrayList<>();

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {

        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        // During the initial application startup, there will be a callback to this method and during that, the results from environment.getPropertySources() contains application.properties property source.
        // However, there will be callback to this method when we hit /actuator/refresh call via HTTP POST as well, and during that time, i am not getting application.properties property source from the results of the method call environment.getPropertySources().
        // This new behaviour started with Spring Boot 2.4.x and the respective compatible Spring cloud combination 2020.0.0. (or any 2020.0.x).
        // This new behaviour is same (as said above) till Spring Boot 3.0.x and the respective compatible spring cloud version.
        // Prior to Spring Boot 2.4, i.e Spring Boot 2.1.x, 2.2.x & 2.3.x and the respective compatible cloud combination,
        // we were always getting application.properties based property source from the results of environment.getPropertySources() method call during application startup and during /actuator/refresh call as well
        // Is it a bug or intentional behaviour? or am i missing something?
        // For custom processing we need application.properties based property source during the call back to this method when we hit /actuator/refresh call.

        // recreating just to throw away the old one
        propertySources = new ArrayList<>();
        System.out.println("-------------------------Property source names obtained during call back--------------------------------");
        for (PropertySource<?> propertySource : environment.getPropertySources()) {
            propertySources.add(propertySource);
            System.out.println("Property source name: "+propertySource.getName());
        }
        System.out.println("-------------------------END of `Property source names obtained during call back`block------------------");
    }


    public List<PropertySource<?>> getPropertySources() {
        return propertySources;
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE - 1;
    }

}
