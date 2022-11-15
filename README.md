# calc-poc

## Description
This is a proof of concept for a calculator microservice.

## Versions
- Spring Boot: 2.7.5
- Java: 17 (Amazon Corretto v17.0.5)
- Maven: 3.8.1

## Modules
There is three modules on the microservice:
 - discoveryserver: This module will setup a [Netflix Eureka service registry](https://github.com/spring-cloud/spring-cloud-netflix) to register posible future microservices.
 - configserver: This module will serve configurations to the microservices.
 - operationmanagement: This is the calculator microservice. Will connect to the discoveryserver and take the configuration from the configserver. This module has a rest API to calculate the operations. For now, only can SUM and SUBTRACT, but is prepared to add more operations easily.

## Execution with Maven
At the microservice root, realize a `clean install`:
`mvn clean install`

This will realize a "clean install" downloading all the dependencies and generating the necessary files on target (including API DTOs and more).

Now, you can execute the modules in orden:
- Go into discoveryserver path and execute: `mvn spring-boot:run`
- Go into configserver path and execute: `mvn spring-boot:run`
- With the two servers up, go into the operationmanagement path and execute: `mvn spring-boot:run`

You can see the Eureka server status opening [localhost:8761](http://localhost:8761/).
You can access the API documentation going to [localhost:8083/swagger-ui/index.html](http://localhost:8083/swagger-ui/index.html) try the request for `/operation` or use some software as Postman to make a `POST` request to `localhost:8083/operation` with a body similar to this:
```
{
  "firstOperand": 1234.5678,
  "secondOperand": 1234.5678,
  "operation": "add"
}
```

## Generate JAR
To generate JAR files, go to the microservice project root and execute: `mvn clean package`. This will create the JARs for the modules.
- Go into discoveryserver/target and execute: `java -jar discoveryserver-0.0.1-SNAPSHOT.jar`
- Go into configserver/target and execute: `java -jar configserver-0.0.1-SNAPSHOT.jar`
- With the two servers up, go into the operationmanagement/target and execute: `java -jar operationmanagement-0.0.1-SNAPSHOT.jar`

Now, you will have access to same tests:
- Eureka server status on [localhost:8761](http://localhost:8761/).
- API documentation and testing on [localhost:8083/swagger-ui/index.html](http://localhost:8083/swagger-ui/index.html).

