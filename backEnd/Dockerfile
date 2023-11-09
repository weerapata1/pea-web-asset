# Use OpenJDK as the base image
# FROM openjdk:11-jre-slim
FROM openjdk:8

# Expose the port your Spring Boot app is listening on
EXPOSE 8080

COPY target/web-asset.jar /web-asset.jar

# Set the working directory inside the container
# WORKDIR /app

# Copy the Spring Boot JAR file and any other necessary files
# COPY target/web-asset.jar /app/web-asset.jar

# Define the command to run your Spring Boot app
ENTRYPOINT ["java", "-jar", "web-asset.jar"]