# Stage 1: Build the WAR package using Maven
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run the executable WAR package with Java 17
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/*.war app.war
EXPOSE 8083
ENV PORT=8083
ENTRYPOINT ["java", "-jar", "app.war"]
