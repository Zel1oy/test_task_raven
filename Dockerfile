FROM openjdk:17-jdk-slim
COPY target/*.jar application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]
EXPOSE 8080
