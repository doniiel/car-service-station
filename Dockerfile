FROM openjdk:17-jdk-slim

LABEL authors="daniyal"

WORKDIR /app

COPY target/sto-service-1.0.0.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]