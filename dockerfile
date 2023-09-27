FROM maven:3.8.3-openjdk-17 AS maquinabuild

COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

COPY --from=maquinabuild /target/JBS-0.1.jar api.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar", "api.jar"]