FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

# Copy the JAR file (use the exact name from your target folder)
COPY target/thymleaf_project-0.0.1-SNAPSHOT.jar app.jar

# Use your custom port (9090 instead of default 8080)
EXPOSE 9090

ENTRYPOINT ["java", "-jar", "app.jar"]