FROM bellsoft/liberica-openjdk-alpine:17

WORKDIR /app

EXPOSE 8080

ARG JAR_FILE
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-XX:MaxRAMPercentage=90.0", "-jar", "app.jar"]