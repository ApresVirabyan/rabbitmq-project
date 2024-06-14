FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY  /target/rabbit-producer-0.0.1-SNAPSHOT.jar /app/rabbit-producer.jar

ENTRYPOINT ["java", "-jar", "/app/rabbit-producer.jar"]