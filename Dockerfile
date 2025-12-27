# ---------- Build stage ----------
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copy everything (NO partial caching)
COPY . .

# Build application
RUN mvn clean package -DskipTests

# ---------- Run stage ----------
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy built jar
COPY --from=build /app/target/saas-backend.jar app.jar

EXPOSE 10000
ENTRYPOINT ["java", "-jar", "app.jar"]
