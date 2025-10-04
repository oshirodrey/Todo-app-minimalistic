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

- 🔙 Login page: [http://localhost:8080](http://localhost:8080)

## 📬 (IMPORTANT) Using Postman for API Creating Accounts and Testing

Here are common Postman requests for interacting with the backend API:

---

### 📝 1. Register a New User

**Method:** `POST`  
**URL:** `http://localhost:8080/api/registration`  
**Headers:**
```json
Content-Type: application/json
```

**Body (JSON):**
```json
{
  "firstName": "Alice",
  "lastName": "Wonderland",
  "email": "alice@example.com",
  "password": "securePassword123"
}
```

**Expected Response:** `200 OK` (or a confirmation message saying check your email)

---

### ✅ 2. Confirm Registration Token

**Method:** `GET`  
**URL:** `http://localhost:8080/api/registration/confirm?token=PASTE_TOKEN_HERE`  

Replace `PASTE_TOKEN_HERE` with the token received via email (or check logs if email sending is mocked).

**Expected Response:** `200 OK` – account is activated.

---

### 🧾 3. Create a Todo Item

**Method:** `POST`  
**URL:** `http://localhost:8080/api/todos`  
**Headers:**
```json
Content-Type: application/json
Authorization: Bearer YOUR_AUTH_TOKEN   <-- if using JWT
```

**Body (JSON):**
```json
{
  "title": "Finish Docker Setup",
  "description": "Wrap up README, test containers"
}
```

**Expected Response:** `200 OK` with created todo object

---

### 📋 4. Get All Todos

**Method:** `GET`  
**URL:** `http://localhost:8080/api/todos`  
**Headers:**
```json
Authorization: Bearer YOUR_AUTH_TOKEN
```

**Expected Response:** List of todos for the authenticated user.


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
