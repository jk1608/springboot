# Spring Boot Samples 


## spring-boot-21-refresh

This project demonstrates the spring boot 2.1.x & spring cloud (Greenwich.SR3) behaviour 
during application startup and /actuator/refresh end point call for the call back-s of 
ApplicationContextInitializer<ConfigurableApplicationContext> with the test case that passes

Java version: JDK 8+
Maven version: 3.6+ or 3.8+

## spring-boot-24-refresh

This project demonstrates the spring boot 2.4.x & spring cloud (2020.0.0) behaviour
during application startup and /actuator/refresh end point call for the call back-s of
ApplicationContextInitializer<ConfigurableApplicationContext> with the test case that fails

Java version: JDK 8+
Maven version: 3.6+ or 3.8+

### spring-boot-21-refresh vs spring-boot-24-refresh

The test case defined in **spring-boot-21-refresh** project with the class **SpringBoot21RefreshApplicationTest** will pass 
while the test case defined in **spring-boot-24-refresh** project with the class **SpringBoot24RefreshApplicationTest** will fail


