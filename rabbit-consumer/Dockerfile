FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY  /target/rabbit-consumer-0.0.1-SNAPSHOT.jar /app/rabbit-consumer.jar

ENTRYPOINT ["java", "-jar", "/app/rabbit-consumer.jar"]