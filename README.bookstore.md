
# 📚 Bookstore – Vollständiges Java Fullstack Beispielprojekt

Das **Bookstore**-Projekt ist der abschließende und umfassendste Teil meiner Fullstack-Java-Demo-Reihe (Teil 7).  
Es demonstriert ein praxisnahes, modernes Setup mit einem vollständigen Tech-Stack:

✅ **Spring Boot Backend**  
✅ **Angular Frontend**  
✅ **PostgreSQL-Datenbank**  
✅ **JWT Login & Security**  
✅ **RESTful API mit Swagger-Dokumentation**  
✅ **Containerisierung mit Docker**  
✅ **CI/CD mit GitHub Actions (in Arbeit)**

> Dieses Projekt eignet sich ideal zur Demonstration meiner Kenntnisse für **Fullstack-Entwicklung**, **Clean Code**, **Microservice-Architektur** sowie für **technische Interviews** oder Bewerbungsprozesse im Java-Umfeld.

---

## 🔧 Funktionen

- 📚 Verwaltung von Büchern (CRUD)
- 🔐 **Login & Authentifizierung** mit **JWT**
- 👥 Benutzerverwaltung
- 🧪 Validierung & Fehlerbehandlung mit HTTP Status Codes
- 🧵 Logging & Debugging
- 🌍 API dokumentiert via Swagger UI (`/swagger-ui.html`)
- 💾 PostgreSQL (alternativ H2 für Dev)
- 🐳 Docker-Setup (Dockerfile & docker-compose)
- 📦 Strukturierte Projektaufteilung (Controller, Service, DTO, Model, Security)

---

## 🔒 **Admin-Login für Benutzer**

Für den Zugriff auf die Anwendung als Admin:

- **Benutzername**: `admin`
- **Passwort**: `admin`

Der Login wird mit JWT durchgeführt und schützt das System vor unbefugtem Zugriff.

---

## 🚀 **Schnelle Anleitung zum Starten**

### 1. **Projekt klonen**

Um das Projekt zu starten, klone das Repository:

```bash
git clone https://github.com/deinname/bookstore.git
cd bookstore
```

### 2. **Backend starten (Spring Boot)**

1. Gehe in das Backend-Verzeichnis:
   ```bash
   cd backend
   ```

2. Baue und starte das Backend:
   ```bash
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```

   Das Backend läuft auf `localhost:8080` und verwendet PostgreSQL.

### 3. **Frontend starten (Angular)**

1. Gehe ins Frontend-Verzeichnis:
   ```bash
   cd frontend
   ```

2. Installiere die Abhängigkeiten:
   ```bash
   npm install
   ```

3. Starte die Entwicklungsumgebung:
   ```bash
   npm start
   ```

   Das Frontend läuft auf `localhost:4200`.

### 4. **Erste Nutzung**

- Besuche die Anwendung im Browser unter [http://localhost:4200](http://localhost:4200).
- Melde dich mit dem Admin-Account (`admin` / `admin`) an, um Zugang zu den Funktionen zu erhalten.

---

## 🧪 **Testing und CI/CD**

- Jedes Mal, wenn Änderungen auf den `main`-Branch gepusht werden, wird automatisch eine CI/CD-Pipeline ausgeführt.
- Die Pipeline umfasst:
  - **Unit-Tests** für Backend und Frontend
  - **Coverage-Reports** für Backend (mit JaCoCo) und Frontend (mit Karma)
  - **Docker Build** und Deployment zu **Okteto**

---

## 🖼️ **Screenshots**

- **Frontend**: Bildschirmansicht der Anwendung und Benutzeroberfläche.
- **Backend**: Test- und Coverage-Berichte.
  
---

## 📄 **License**

Dieses Projekt ist unter der MIT-Lizenz lizenziert.
