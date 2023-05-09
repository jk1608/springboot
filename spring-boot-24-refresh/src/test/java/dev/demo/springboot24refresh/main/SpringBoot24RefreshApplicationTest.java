package dev.demo.springboot24refresh.main;

import dev.demo.springboot24refresh.bootstrap.CustomApplicationContextInitializer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.env.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringBoot24RefreshApplication.class,
        webEnvironment= RANDOM_PORT)
public class SpringBoot24RefreshApplicationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CustomApplicationContextInitializer customApplicationContextInitializer;

    @Test
    void applicationPropertiesTest() {
        List<PropertySource<?>> propertySources = customApplicationContextInitializer.getPropertySources();
        Set<String> propertySourceNames = extractPropertySourceNames(propertySources);
        assertTrue(propertySourceNames.contains("Config resource 'class path resource [application.properties]' via location 'optional:classpath:/'"));
        makeRefreshEndPointCall();
        propertySources = customApplicationContextInitializer.getPropertySources();
        propertySourceNames = extractPropertySourceNames(propertySources);
        assertTrue(propertySourceNames.contains("Config resource 'class path resource [application.properties]' via location 'optional:classpath:/'"));
    }

    private void makeRefreshEndPointCall() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.ALL));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> refreshResults = restTemplate.postForEntity("http://localhost:" + port + "/actuator/refresh", entity, String.class);
        assertThat(refreshResults.getStatusCode(), is(HttpStatus.OK));
    }

    private Set<String> extractPropertySourceNames(List<PropertySource<?>> propertySources) {
        return propertySources.stream().map(source -> source.getName()).collect(Collectors.toSet());
    }

}


