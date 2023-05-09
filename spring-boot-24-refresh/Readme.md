# Project to demonstrate spring boot refresh issue
# Problem: Main/Application context's Environment is not providing application.properties property source to the call backs of implementations of ApplicationContextInitializer<ConfigurableApplicationContext> during **/actuator/refresh** end point call

* <b>application.properties</b> based property source is not available in the call back methods of the registered custom implementation of 
     ```ApplicationContextInitializer<ConfigurableApplicationContext> ``` 
    in spring boot app for customization during context refresh via the actuator end point <b>/actuator/refresh</b>.\
    This issue started with Spring Boot version 2.4.x and Spring cloud version 2020.0.0.
    This behaviour is same from Spring Boot 2.4.x version to the latest spring boot version 3.0.x with their respective compatible cloud version.
    This issue not seen from Spring Boot version 2.1.x to Spring Boot version 2.3.x with their respective compatible cloud versions.
    
*    We can examine this through the test case **applicationPropertiesTest()** defined in the test class **ApplicationPropertiesTest**. 
     Similar kind of test will fail with spring boot 2.5.x, spring boot 2.6.x, spring boot 2.7.x and spring boot 3.0.x as well.
     However, the similar kind of test will not fail with spring boot 2.1.x, spring boot 2.2.x and spring boot 2.3.x with their 
     respective spring cloud compatibility version. So there is a change in behaviour from spring boot 2.3.x to spring boot 2.4.x.

*    Not sure whether it is a bug or intentional behaviour from Spring Boot 2.4.x

*   To see the behaviour of Spring boot 2.1.x, please check out the branch 2.1.x and that has test case that will pass as the callback 
    will always have reference to **application.properties** based property source 

  * List of property source names obtained during app start up, and this list has application.properties:
  
      `
    -------------------------Property source names obtained during call back--------------------------------
    Property source name: configurationProperties
    Property source name: Inlined Test Properties
    Property source name: systemProperties
    Property source name: systemEnvironment
    Property source name: random
    Property source name: springCloudClientHostInfo
    Property source name: Config resource 'class path resource [application.properties]' via location 'optional:classpath:/'
    Property source name: Config resource 'class path resource [bootstrap.properties]' via location 'optional:classpath:/'
    Property source name: springCloudDefaultProperties
    Property source name: cachedrandom
    -------------------------END of `Property source names obtained during call back`block------------------
        `
    
* List of property source names obtained during /actuator/refresh call (with HTTP POST call), and this list doesn't has application.properties based property source name:
        
        `
      -------------------------Property source names obtained during call back--------------------------------
      Property source name: configurationProperties
      Property source name: refreshArgs
      Property source name: systemProperties
      Property source name: systemEnvironment
      Property source name: defaultProperties
      Property source name: Config resource 'class path resource [bootstrap.properties]' via location 'optional:classpath:/'
      Property source name: springCloudDefaultProperties
      Property source name: random
      Property source name: springCloudClientHostInfo
      Property source name: cachedrandom
      -------------------------END of `Property source names obtained during call back`block------------------
        `
* The above output can be seen on console when we run the test case **SpringBoot24RefreshApplicationTest**

## Runtime 

Project Root: spring-boot-24-refresh
Build Tool: Maven
Java version: 8+

Test results: The test case defined in SpringBoot21RefreshApplicationTest will fail











