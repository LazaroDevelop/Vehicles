# Use an official Java runtime as a parent image
FROM amazoncorretto:21.0.4-alpine3.18

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/vehicle-api-service-1.0.0.jar app.jar

# Expose the port that the application will run on
EXPOSE 8082

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
