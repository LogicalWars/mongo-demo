FROM alpine/java:21-jdk

COPY target/mongo-demo-0.0.1-dev.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]