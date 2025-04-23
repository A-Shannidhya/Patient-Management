FROM gradle:8.7.0-jdk21 AS builder

WORKDIR /app

# Copy the contents of the current directory (patient-service) to /app in the container
COPY . .

RUN gradle build --no-daemon --stacktrace --info > build_logs.txt || tail -n 100 build_logs.txt

FROM openjdk:21-jdk AS runner

WORKDIR /app

# Copy the jar file from the builder stage
COPY --from=builder /app/build/libs/patient-service-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 8000

ENTRYPOINT ["java", "-jar", "app.jar"]
