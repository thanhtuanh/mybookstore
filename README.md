# 📚 Bookstore – Vollständiges Java Fullstack Beispielprojekt

Das **Bookstore**-Projekt ist der abschließende und umfassendste Teil meiner Fullstack-Java-Demo-Reihe (Teil 7).  
Es demonstriert ein praxisnahes, modernes Setup mit einem vollständigen Tech-Stack:

✅ **Spring Boot Backend**  
✅ **Angular Frontend**  
✅ **PostgreSQL-Datenbank**  
✅ **JWT Login & Security**  
✅ **RESTful API mit Swagger-Dokumentation**  
✅ **Containerisierung mit Docker & docker-compose**  
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
- 🐳 Docker-Setup mit `docker-compose`
- 📦 Strukturierte Projektaufteilung (Controller, Service, DTO, Model, Security)

---

## 🔒 **Admin-Login für Benutzer**

Für den Zugriff auf die Anwendung als Admin:

- **Benutzername**: `admin`
- **Passwort**: `admin`

Der Login wird mit JWT durchgeführt und schützt das System vor unbefugtem Zugriff.

---

## 🚀 **Schnelle Anleitung mit Docker (empfohlen)**

Mit `docker-compose` kannst du die komplette Anwendung mit einem Befehl starten.

### ✅ Voraussetzungen

- [Docker](https://www.docker.com/) und [Docker Compose](https://docs.docker.com/compose/) installiert

### ▶️ Anwendung starten

1. Klone das Repository:

   ```bash
   git clone https://github.com/thanhtuanh/mybookstore.git
   cd mybookstore
   ```

2. Starte alle Komponenten (Datenbank, Backend, Frontend):
   docker-compose up --build

3. Öffne das Frontend im Browser:
   👉 http://localhost:4200
4. Melde dich an mit:
   Benutzername: admin
   Passwort: admin

## 🧪 **Testing und CI/CD**

- Jedes Mal, wenn Änderungen auf den `main`-Branch gepusht werden, wird automatisch eine CI/CD-Pipeline ausgeführt.
- Die Pipeline umfasst:
  - **Unit-Tests** für Backend und Frontend
  - **Coverage-Reports** für Backend (mit JaCoCo) und Frontend (mit Karma)
  - **Docker Build** und Deployment zu **Okteto**

---

## 🖼️ Screenshots & Dokumentation (PDF)

Hier findest du die wichtigsten Screenshots zur Benutzeroberfläche, Backend-Logik und dem CI/CD-Prozess.

- 📘 [Frontend (PDF)](assets/screenshots/frontend.pdf)
- 🛠️ [Backend (PDF)](assets/screenshots/backend.pdf)
- 🚀 [Testing & Deployment (PDF)](assets/screenshots/testing-deploy.pdf)


---

## 📄 **License**

Dieses Projekt ist unter der MIT-Lizenz lizenziert.
