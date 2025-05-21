FROM openjdk:23
LABEL authors="Steve"
WORKDIR /app
COPY    target/microservicio-mensajeria-0.0.1-SNAPSHOT.jar /app
ENTRYPOINT ["java", "-jar", "microservicio-mensajeria-0.0.1-SNAPSHOT.jar"]
