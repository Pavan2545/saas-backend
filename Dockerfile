# ---------- Build stage ----------
FROM maven:3.8.8-eclipse-temurin-11 AS build
WORKDIR /app

# Copy pom first for dependency caching
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# ---------- Run stage ----------
FROM eclipse-temurin:11-jre
WORKDIR /app

# Copy the built jar
COPY --from=build /app/target/saas-backend.jar app.jar

# Expose Spring Boot port
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
