# Use a highly reliable and minimal Temurin JRE 17 image
# Old (Failing) Line,New (Correct) Line
FROM eclipse-temurin:21-jre-alpine
# Set working directory inside container
WORKDIR /app

# Copy jar from target to container
COPY target/*.jar app.jar

# Expose default Spring Boot port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]