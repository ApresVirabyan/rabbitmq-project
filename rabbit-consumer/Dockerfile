FROM maven:3.8.4-openjdk-17

ENV PORT 9001

WORKDIR /app

COPY  /target/rabbit-consumer-0.0.1-SNAPSHOT.jar /app/rabbit-consumer.jar

ENTRYPOINT ["java", "-jar", "/app/rabbit-consumer.jar"]