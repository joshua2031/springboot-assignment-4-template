# Builder stage
FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /build

COPY gradlew .
COPY gradle gradle
COPY build.gradle* settings.gradle* gradle.properties* ./
RUN chmod +x ./gradlew && ./gradlew dependencies --no-daemon || true

COPY . .
RUN ./gradlew bootJar --no-daemon

# Runtime stage
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /build/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
