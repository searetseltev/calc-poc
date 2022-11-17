# calc-poc

## :page_facing_up: Description
This is a proof of concept for a calculator microservice.

## :computer: Versions
- Spring Boot: 2.7.5
- Java: 17 (Amazon Corretto v17.0.5)
- Maven: 3.8.1

## :package: Modules
### Discovery Server
#### Description
This module will setup a [Netflix Eureka service registry](https://github.com/spring-cloud/spring-cloud-netflix) to register posible future microservices.

### Config Server
#### Description
This module will serve configurations to the microservices. 
#### External configurations
As proof of concept, the configuration served to the `operationmanagement` microservice will be downloaded from another server. The configuration for the `operationmanagement` is at [https://github.com/searetseltev/microservices](https://github.com/searetseltev/microservices).

### Operation Management
#### Description
This is the calculator microservice. Will connect to the Discovery Server and take the configuration from the Config Server. 

#### API definition and generation
For this module, there is a `yaml` file on `src/main/resources/static/calc-api.yml` to define the API from openApi (using the specifications for `3.0.3`). This file is used to generate the interface for the endpoints and the necessary DTOs with the `openapi-generator` plugin automatically launched at build.

To help testing the API, `swagger-ui` it's linked to the `calc-api.yml` to be consistent with the actual build.

The rest API has been prepared to calculate certain operations but it's prepared to create more options. The operation request, can receive multiple values to do the operation and can return multiple values too. Originally, the request is only to implement ADD and SUBTRACT... but at the future, it's posible have to implement quadratic equations (returning two results) or operations with a limited number of elements (as calculate Fibonacci serie giving a top limit). 
On the implementation there is an usecase component for every operation and them will be called from the operations service.
As proof of concept, there is an operation called `bigger_and_lower`, that returns two values, the bigger of the list and the lower one.

## :running: Running the project
### Maven
At the microservice root, realize a `clean install`:
```mvn clean install```

This will realize a "clean install" downloading all the dependencies and generating the necessary files on target (including API DTOs and more).

Now, you can execute the modules in order:
- Go into discoveryserver path and execute: `mvn spring-boot:run`
- Go into configserver path and execute: `mvn spring-boot:run`
- With the two servers up, go into the operationmanagement path and execute: `mvn spring-boot:run`

You can see the Eureka server status opening [localhost:8761](http://localhost:8761/).
You can access the API documentation going to [localhost:8083/swagger-ui/index.html](http://localhost:8083/swagger-ui/index.html) try the request for `/operation` or use some software as Postman to make a `POST` request to `localhost:8083/operation` with a body similar to this:
```
{
  "values": [1234.5678, 1234.5678], 
  "operation": "add"
}
```

### Java JAR
To generate JAR files, go to the microservice project root and execute: `mvn clean package`. This will create the JARs for the modules.
- Go into discoveryserver/target and execute: `java -jar discoveryserver-0.0.1-SNAPSHOT.jar`
- Go into configserver/target and execute: `java -jar configserver-0.0.1-SNAPSHOT.jar`
- With the two servers up, go into the operationmanagement/target and execute: `java -jar operationmanagement-0.0.1-SNAPSHOT.jar`

Now, you will have access to same tests:
- Eureka server status on [localhost:8761](http://localhost:8761/).
- API documentation and testing on [localhost:8083/swagger-ui/index.html](http://localhost:8083/swagger-ui/index.html).

## :book: Tracer library
At the root POM, there is a dependency for tracer library. It could be dropped at lib folder on project and easily add it giving her route to the project... but at deploy, it's possible that this library has not been copied on the same route. The solution was integrate the lib on a local maven repository. 

### How to
At start, we have three files:
- tracer-1.0.0.jar
- tracer-1.0.0-javadoc.jar
- tracer-1.0.0-sources.jar

1. Create a `lib` folder at the root of the project: `microservice/lib`.
1. To install the `jar` for the library, execute this maven command:
   ```mvn install:install-file -Dfile=<path_to_jar_file>/tracer-1.0.0.jar -DgroupId=io.corp.calculator -DartifactId=tracer -Dversion=1.0.0 -Dpackaging=jar -DcreateChecksum=true```
1. Now, on your local maven repository (normally on `<UserFolder>/.m2`) will have a folder called `.m2/io/corp/calculator/tracer/1.0.0`.
1. We need to recreate this tree folder at the `lib` folder of the project: `microservice/lib/io/corp/calculator/tracer/1.0.0`.
1. Copy files `maven-metadata-local.xml`, `maven-metadata-local.xml.md5` and `maven-metadata-local.xml.sha1` from `.m2/io/corp/calculator/tracer` to `microservice/lib/io/corp/calculator/tracer` and rename them to `maven-metadata.xml`, `maven-metadata.xml.md5` and `maven-metadata.xml.sha1`.
1. Copy all files from `.m2/io/corp/calculator/tracer` to `microservice/lib/io/corp/calculator/tracer/1.0.0`.
1. Copy `tracer-1.0.0-javadoc.jar` and `tracer-1.0.0-sources.jar` to `microservice/lib/io/corp/calculator/tracer/1.0.0`.
1. Edit the root POM and add a local repository (name is irrelevant):
   ```
    <repositories>
        <repository>
            <id>localRepositoryId</id>
            <name>localRepositoryName</name>
            <url>file://${project.basedir}/lib</url>
        </repository>
    </repositories>
   ```
1. Add the dependency to the root POM too:
   ```
        <dependency>
            <groupId>io.corp.calculator</groupId>
            <artifactId>tracer</artifactId>
            <version>1.0.0</version>
        </dependency>
   ```
1. If you want to force download all the sources and java doc, you can use this command:
   ```mvn dependency:sources dependency:resolve -Dclassifier=javadoc```
1. The last thing, will be create a configuration `Bean`, so we can inject it where we want:
   ```
   @Configuration
   public class TracerConfiguration {
     @Bean
     public TracerImpl getTracerImpl() {
        return new TracerImpl();
     }
   }
   ```
