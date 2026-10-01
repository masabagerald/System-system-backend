FROM eclipse-temurin:21-jre

WORKDIR /app

RUN useradd -r -u 1001 spring

COPY target/*.jar app.jar

RUN chown spring:spring app.jar

USER spring

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "app.jar"]

