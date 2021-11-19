FROM maven:3.8.3-jdk-11 AS builder
COPY . src
WORKDIR src
RUN mvn install package
FROM openjdk:11
COPY --from=builder src/target/api-0.0.1-SNAPSHOT.jar api.jar
VOLUME /tmp
EXPOSE 8080
ENTRYPOINT ["java","-jar","api.jar"]