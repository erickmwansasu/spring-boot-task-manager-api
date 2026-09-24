# Task Manager REST API

A robust, production-ready RESTful API built with **Spring Boot** to manage user tasks, track completion statuses, and streamline daily productivity.

## Features

- **Task Management**: Full CRUD operations (Create, Read, Update, Delete) for managing tasks.
- **Filtering & Searching**: Sort tasks by due date, priority, or completion status.
- **Validation**: Strict input validation using Spring Boot Starter Validation.
- **Database Integration**: Powered by Spring Data JPA with an H2 (in-memory) or PostgreSQL database.

## Tech Stack

- **Java 21**
- **Spring Boot 3.x** (Spring Web, Spring Data JPA)
- **Database**: PostgresQL (Development) / To Decide (Production)
- **Build Tool**: Maven

## ⚙️ Getting Started

### Prerequisites
- **JDK 17** or higher installed
- **Maven** 3.8+ installed (or use the included wrapper `./mvnw`)

### Installation & Running Locally

1. **Clone the repository:**
   ```bash
   git clone https://github.com/erickmwansasu/spring-boot-task-manager-api
   cd YOUR-REPOSITORY-NAME
   ```

2. **Build the application:**
   ```bash
   ./mvnw clean install
   ```

3. **Run the application:**
   ```bash
   ./mvnw spring-boot:run
   ```
   The server will start locally at `http://localhost:8080`.

## 📁 API Endpoints

### Tasks Resource

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| **GET** | `/api/tasks` | Retrieve all tasks (supports query filtering) |
| **GET** | `/api/tasks/{id}` | Retrieve a specific task by its ID |
| **POST** | `/api/tasks` | Create a new task |
| **PUT** | `/api/tasks/{id}` | Update an existing task completely |
| **DELETE** | `/api/tasks/{id}` | Delete a task |

### Sample JSON Request Body (`POST /api/tasks`)
```json
{
  "title": "Finish Project Documentation",
  "description": "Write a comprehensive README and API contract documentation.",
  "dueDate": "2026-12-31",
  "priority": "HIGH",
  "completed": false
}
```

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
