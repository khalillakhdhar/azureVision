# Utilisez une image de base officielle Java avec une version spécifique, par exemple Java 11
FROM openjdk:11-jdk-slim as build

# Copiez les fichiers Maven et téléchargez les dépendances pour optimiser la construction de l'image
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
RUN ./mvnw dependency:go-offline -B

# Copiez les sources de l'application
COPY src src

# Construisez l'application
RUN ./mvnw package -DskipTests

# Utilisez une image Java légère pour l'exécution
FROM openjdk:11-jre-slim

# Copiez le fichier JAR de l'étape de construction
COPY --from=build /target/*.jar app.jar

# Exposez le port sur lequel votre application s'exécute, par exemple 8080
EXPOSE 8080

# Démarrez l'application
ENTRYPOINT ["java","-jar","/app.jar"]
