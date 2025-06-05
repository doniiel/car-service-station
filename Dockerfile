FROM openjdk:21-jdk-slim

LABEL authors="daniyal"

WORKDIR /app

COPY target/sto-1.0.0-SNAPSHOT.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Xms512m", "-Xmx1g", "-jar", "/app/app.jar"]