# Use the base image with Java 8 and Alpine Linux
FROM openjdk:8

# Set the working directory in the container
WORKDIR /app

# Copy the compiled Spring Boot application JAR file into the container
COPY target/json-schema-validator-0.0.1-SNAPSHOT.jar /app/json-schema-validator-0.0.1-SNAPSHOT.jar

# Expose the port that the Spring Boot application listens on
EXPOSE 8080

# Set the command to run the Spring Boot application when the container starts
CMD ["java", "-jar", "-Dspring.profiles.active=${ENV}", "json-schema-validator-0.0.1-SNAPSHOT.jar"]
