FROM gradle:8.7.0-jdk21 AS builder

WORKDIR /app

COPY . .

RUN gradle build --no-daemon --stacktrace --info > build_logs.txt || tail -n 100 build_logs.txt

FROM openjdk:21-jdk AS runner

WORKDIR /app

COPY --from=builder /app/build/libs/analytics-service-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
