# 📋 Todo App (Spring Boot + React + MySQL)

This is a full-stack Todo application with:

- 🧠 **Spring Boot** backend (REST API)
- 🌐 **React** frontend (Create React App)
- 🐬 **MySQL** database
- 🐳 All services are containerized using **Docker Compose**

---

## 🚀 Getting Started

### ✅ Prerequisites

Make sure you have the following installed:

- [Docker Desktop](https://www.docker.com/products/docker-desktop/) (includes Docker Compose)
- Git (optional, to clone this repo)

---

## 📦 Project Structure

```
Todo/
├── docker-compose.yml          # Compose file for all services
├── Dockerfile                  # Backend Dockerfile (Spring Boot)
├── pom.xml                     # Maven project config
├── src/                        # Spring Boot source code
├── frontend/
│   ├── Dockerfile.dev          # React development image
│   ├── Dockerfile              # React production image (optional)
│   ├── package.json
│   └── src/
```

---

## 🧪 Run the App (via Docker Compose)

### Step 1: Open Terminal in the `Todo/` folder

```
cd Todo
```

### Step 2: Build and run all containers

```
docker compose up -d --build
```

This will:
- Pull the MySQL image
- Build the backend (Spring Boot) and frontend (React)
- Start all services in the background (`-d`)

### Step 3: Visit the app!

- 🔙 Login page: [LOGIN PAGE](http://localhost:8080)

### Want to reset all data?
Run:
```bash
docker compose down -v
```

## 🛠 Available Scripts

### Rebuild everything
```bash
docker compose build --no-cache
docker compose up -d
```

### View logs
```bash
docker compose logs -f
```

### Shut everything down
```bash
docker compose down
```
