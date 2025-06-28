#
# Build stage
#
FROM maven:3-eclipse-temurin-21 AS build
WORKDIR /app

COPY src ./src
COPY pom.xml .

RUN mvn clean package -DskipTests

#
# Package stage
#
FROM eclipse-temurin:21-alpine
WORKDIR /app

COPY --from=build /app/target/coin-calculator-backend-1.0-SNAPSHOT.jar coincalculator.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","coincalculator.jar","server","config.yml"]