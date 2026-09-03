# Stage 1: Build application with Maven
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:17-jdk
WORKDIR /app
# This copies whichever file Maven built (.jar or .war)
COPY --from=build /app/target/*.*ar /app/app.jar
EXPOSE 8083
ENV PORT=8083
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
