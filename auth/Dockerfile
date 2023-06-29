# Dockerfile for the auth service
FROM openjdk:17-alpine
WORKDIR /app
COPY ./target/auth-*.jar /app/auth-service.jar
ENTRYPOINT ["java", "-jar", "/app/auth-service.jar"]
