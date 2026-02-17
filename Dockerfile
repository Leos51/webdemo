# Stage 1: Build avec Maven
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copier pom.xml et télécharger les dépendances
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copier le code source et builder
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Runtime avec Tomcat
FROM tomcat:10.1-jdk21-temurin-jammy

# Supprimer les applications par défaut de Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Copier le WAR depuis le stage de build
COPY --from=build /app/target/*.jar /usr/local/tomcat/webapps/ROOT.jar

# Exposer le port
EXPOSE 8080

# Démarrer Tomcat
CMD ["catalina.sh", "run"]