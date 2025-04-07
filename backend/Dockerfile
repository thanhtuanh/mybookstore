# Stage 1: Build-Stage (mit Maven)
FROM eclipse-temurin:17-jdk as builder

# Arbeitsverzeichnis im Container setzen
WORKDIR /app

# Kopiere das gesamte Verzeichnis "backend" (einschließlich pom.xml und src) ins Container-Verzeichnis
COPY ./backend /app

# Stelle sicher, dass mvnw ausführbar ist
RUN chmod +x mvnw

# Baue das Projekt, ohne die Tests auszuführen
RUN ./mvnw clean package -DskipTests
