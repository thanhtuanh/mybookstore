# Stage 1: Build-Stage (mit Maven)
FROM eclipse-temurin:17-jdk as builder

# Arbeitsverzeichnis im Container setzen
WORKDIR /app

# Kopiere nur die benötigten Dateien (pom.xml und den Quellcode)
COPY ./backend/pom.xml /app/pom.xml
COPY ./backend/src /app/src

# Stelle sicher, dass mvnw ausführbar ist
RUN chmod +x mvnw

# Baue das Projekt, ohne die Tests auszuführen
RUN ./mvnw clean package -DskipTests
