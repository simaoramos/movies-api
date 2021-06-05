# **Movies-API**
This is a Maven and Spring Boot API I created as a POC including some design patterns that I find useful or just like to learn more about.

## **Features/tools**
Here are some features or tools used on this project:
* Maven profiles;
* Dockerfile;
* Hexagonal architecture;
* Error handling (ControllerAdvice);
* Spring Devtools;
* H2 in memory database.

TODO:
* PostgreSQL database;
* Unit tests;
* Mulitenancy;
* Oauth2;
* OpenAPI (Swagger);
* Monitoring;
* Etc.

## **Running the project**
Here is a step by step how to run this project:
* First be sure to be in the root project folder (same path as Dockerfile and pom.xml);
* Then run the following command in order to generate the .jar file:
    ```shell
    mvn clean package
    ```
* Once the .jar file is generated on target folder, run the following command to generate the docker image:
    ```shell
    docker build -t simaoramos/movies-api .
    ```
* Next run the docker image with the following command: 
    ```shell
    docker run -p 8080:8080 --name movies-api simaoramos/movies-api
    ```

### **Remote debug on Intellij IDEA**
In case you're running the application within Docker, Intellij IDEA provides a tool to remote debugging your project, that's why the following code was added in the Dockerfile entrypoint:
```dockerfile
ENTRYPOINT ["java", "-Xdebug", "-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "/movies-api.jar"]
```

This allows us to specify that on runtime, the program will provide at the `5005` port a way to connect a remote debug.
The first log line will be `Listening for transport dt_socket at address: 5005`.

Then a little change has to be made on the docker command to run the image specifying either the new port:
```shell
docker run -p 8080:8080 -p 5005:5005 --name movies-api simaoramos/movies-api
```

Now lets set up the Intellij to run the remote debug that will connect on the remote server:
* In the `Run/Debug Configurations`, add a new configuration choosing the `Remote JVM Debug` option;
* Name it as you prefer, e.g. `MoviesApiDebug`;
* The rest of the fields will remain with default values:
  * `Debugger mode` option will remain with `Attach to remote JVM`;
  * `Transport` option will remain with `Socket` value;
  * `Host` too will remain with `localhost` value as we're running on a local machine;
  * `Port` with `5005` default value.

Run this configuration on debug mode, and you're ready to go.

### **Live reload with Spring Devtools**
Spring Devtools provides a way to avoid the process of repackaging and reexecuting the docker build/run every time you make any changes to the source code.

To be able to do this, some config were added to the [pom file](pom.xml):
* First the Spring Devtools dependency was added:
  ```xml
  <!-- Spring Devtools -->
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
    <optional>true</optional>
  </dependency>
  ```
* Then on every profile, the flag property `excludeDevtools` was defined to receive true or false, depending on the scope you prefer. In this case it is flagged false only on dev environment profile:
  ```xml
  <profile>
    <id>dev</id>
    <activation>
      <activeByDefault>true</activeByDefault>
    </activation>
    <properties>
      <activatedProperties>dev</activatedProperties>
      <excludeDevtools>false</excludeDevtools>
    </properties>
  </profile>
  ```
* Spring Devtools also need a secret string to be matched on live reload, so on the [application-dev.yml file](/src/main/resources/application-dev.yml) was added the following property:
  ```yaml
  spring:
    devtools:
      remote:
        secret: secret@devtools
  ```
* Lastly a `Run/Debug Configuration` must be created to listen the changes made to the source code and reload our API with the updated lines:
  * Add a new configuration under the `Application` template;
  * Name it as you prefer, e.g. `MoviesApiLiveReload`;
  * Select `org.springframework.boot.devtools.RemoteSpringApplication` as the main class;
  * On program arguments, put `http://localhost:8080/v1` as we're running on a local machine;
  * Apply and run it.

That's it! Every time you make a change to the source code and build it, the changes will be reflected on the server.