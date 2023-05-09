package dev.demo.springboot21refresh.bootstrap;

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
        // During the initial application startup, results from environment.getPropertySources() contains application.properties property source.
        // During /actuator/refresh call also, i am getting application.properties based property source from the results of environment.getPropertySources()
        // From Spring boot 2.4.x to till Spring boot 3.0.x, we are not seeing this behaviour as we are not getting application.properties based
        // property source from the results of environment.getPropertySources().
        // For custom processing we need application.properties based property source during the call back to this method, triggered by /actuator/refresh call
        System.out.println("-------------------------Property source names obtained during call back--------------------------------");
        // recreating just to throw away the old one
        propertySources = new ArrayList<>();
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
