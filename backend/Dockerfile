# Stage 1: Build-Stage (mit Maven)
FROM eclipse-temurin:17-jdk as builder

# Arbeitsverzeichnis im Container setzen
WORKDIR /app

# Kopiere alle Dateien ins Container-Verzeichnis
COPY . .

# Stelle sicher, dass mvnw ausführbar ist
RUN chmod +x mvnw

# Baue das Projekt, ohne die Tests auszuführen
RUN ./mvnw clean package -DskipTests

# Stage 2: Production-Stage (mit Java Runtime Environment)
FROM eclipse-temurin:17-jre

# Kopiere die gebaute JAR-Datei ins Produktions-Image
COPY --from=builder /app/target/bookstore-backend-0.0.1-SNAPSHOT.jar /app/bookstore-backend.jar

# Setze den Entry-Point zum Starten des JAR-Files
ENTRYPOINT ["java", "-jar", "/app/bookstore-backend.jar"]

# Setze den Standard-Port auf 8080 (optional, für den Fall, dass du den Port explizit in der App ändern möchtest)
EXPOSE 8080
