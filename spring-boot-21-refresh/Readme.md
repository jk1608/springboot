# Spring Boot Refresh end point behaviour in Spring boot 2.1.x 

*  **application.properties** based property source will be available for reference in the call back method of
   **CustomApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext>**
  during the start up of the application and during the /actuator/refresh end point call as well.
*  This can be examined through the test case **applicationPropertiesTest()** defined in the test class  **ApplicationPropertiesTest** as this test case will pass.
* To run the test case, please checkout the code from 2.1.x branch.

* List of property source names obtained during app start up, and this list has application.properties:

        `
        -------------------------Property source names obtained during call back--------------------------------
        Property source name: configurationProperties
        Property source name: Inlined Test Properties
        Property source name: systemProperties
        Property source name: systemEnvironment
        Property source name: random
        Property source name: applicationConfig: [classpath:/application.properties]
        Property source name: springCloudClientHostInfo
        Property source name: applicationConfig: [classpath:/bootstrap.properties]
        Property source name: defaultProperties
        -------------------------END of `Property source names obtained during call back`block------------------    

      `
* List of property source names obtained during /actuator/refresh call (with HTTP POST call), and this list doesn't has application.properties based property source name:

          `
            -------------------------Property source names obtained during call back--------------------------------
            Property source name: configurationProperties
            Property source name: refreshArgs
            Property source name: systemProperties
            Property source name: systemEnvironment
            Property source name: random
            Property source name: applicationConfig: [classpath:/application.properties]
            Property source name: springCloudClientHostInfo
            Property source name: applicationConfig: [classpath:/bootstrap.properties]
            Property source name: defaultProperties
            -------------------------END of `Property source names obtained during call back`block------------------        `
* The above output can be seen on console when we run the test case defined **SpringBoot21RefreshApplicationTest**

## Runtime

Project Root: spring-boot-21-refresh
Build Tool: Maven
Java version: 8+

Test results: The test case defined in SpringBoot21RefreshApplicationTest will pass



