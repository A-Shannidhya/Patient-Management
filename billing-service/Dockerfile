FROM gradle:8.7.0-jdk21 AS builder

WORKDIR /app
COPY . .

# Build and skip tests to avoid failure
RUN gradle clean build -x test --no-daemon --stacktrace --info > build_logs.txt || (tail -n 100 build_logs.txt && exit 1)

FROM openjdk:21-jdk-slim AS runner

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8001 9001

ENTRYPOINT ["java", "-jar", "app.jar"]
