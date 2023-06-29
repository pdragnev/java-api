# Dockerfile for the auth service
FROM openjdk:17-alpine
WORKDIR /app
COPY ./target/customers-*.jar /app/customers-service.jar
ENTRYPOINT ["java", "-jar", "/app/customers-service.jar"]