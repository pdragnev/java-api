# Dockerfile for the notification service
FROM openjdk:17-alpine
WORKDIR /app
COPY ./target/notifications-*.jar /app/notifications-service.jar
ENTRYPOINT ["java", "-jar", "/app/notifications-service.jar"]