# Use Java 8
FROM openjdk:8-jdk-alpine

# Create app directory
WORKDIR /app

# Copy source code
COPY src/ ./src/

# Compile Java
RUN javac src/com/clinic/booking/Main.java

# Run the application
CMD ["java", "-cp", "src", "com.clinic.booking.Main"]