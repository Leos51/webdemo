## Stage 1: Build avec Maven
#FROM maven:3.9.9-eclipse-temurin-21 AS build
#WORKDIR /app
#
#COPY pom.xml .
#RUN mvn dependency:go-offline -B
#
#COPY src ./src
#RUN mvn clean package -DskipTests
#
## Stage 2: Runtime avec Java uniquement
#FROM eclipse-temurin:21-jre-jammy
#
#WORKDIR /app
#
## Copier le JAR depuis le stage de build
#COPY --from=build /app/target/*.jar app.jar
#
#EXPOSE 9001
#
## Lancer le JAR Spring Boot directement
#ENTRYPOINT ["java", "-jar", "app.jar"]
FROM openjdk:21
ENV APP_HOME=/app
EXPOSE 9001
WORKDIR $APP_HOME
COPY target/*jar /app/app.jar
CMD ["java", "-jar", "app.jar"]