VERSION 0.8
FROM maven:3.9.9-eclipse-temurin-21
WORKDIR /src

build:
    COPY pom.xml ./
    COPY ./src src
    RUN rm -r ./src/main/resources/exampleDay
    RUN mvn clean package install
    SAVE ARTIFACT src/main/resources/* AS LOCAL ./output/
    SAVE ARTIFACT target/*.jar AS LOCAL ./output/