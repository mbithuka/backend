FROM gradle:jdk17 AS builder

WORKDIR /app

# Copy the Gradle files and wrapper 
COPY build.gradle settings.gradle ./
COPY gradlew ./
COPY gradle ./gradle

# Copy the application source code
COPY src ./src

# Build the application
RUN ./gradlew build -x test

# Stage 2: Build the final Docker image with MySQL
FROM openjdk:21-jdk


# Copy the built Spring Boot application JAR file from the previous stage
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose the Spring Boot application port
EXPOSE 8080

# Start the Spring Boot application
CMD ["java", "-jar", "app.jar"]
