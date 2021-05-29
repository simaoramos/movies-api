FROM openjdk:15-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} movies-api.jar
EXPOSE 8080
EXPOSE 5005
ENTRYPOINT ["java", "-Xdebug", "-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "/movies-api.jar"]