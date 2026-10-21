# 🎬 My OTT Platform

A full-stack, enterprise-grade streaming catalog and user management platform built using **Spring Boot**, **Spring Security (JWT)**, **Hibernate / JPA**, and **Tailwind CSS**. This application enables users to securely authenticate, explore a rich catalog of movies and TV series with real-time filtering and keyword search, curate personal watchlists, manage account details, and choose membership tiers.

---

## 🛠️ Tech Stack

### **Backend**

* **Framework:** Spring Boot (3.x)
* **Data Access:** Spring Data JPA, Hibernate
* **Security & Auth:** Spring Security, JSON Web Tokens (JWT), BCrypt Password Hashing
* **Database:** MySQL / H2 Relational Database
* **Build Tool:** Apache Maven
* **Validation:** Jakarta Validation (`jakarta.validation.constraints.*`)

### **Frontend**

* **Markup & Styling:** HTML5, Tailwind CSS
* **Scripting:** Vanilla JavaScript (ES6+), Fetch API
* **Client Storage:** Browser `localStorage` for JWT token persistence

---

## 📂 Project Architecture & Directory Structure

The project follows a standard **3-Tier Layered Architecture** ensuring clear separation of concerns, testability, and maintainability:

```text
src/
├── main/
│   ├── java/com/college/myproject/
│   │   ├── MyprojectApplication.java         # Main Spring Boot Application Entry Point
│   │   ├── DataLoader.java                   # Bootstraps initial movie catalog & seed user data
│   │   ├── controller/
│   │   │   ├── AuthController.java           # Handles user login and registration endpoints
│   │   │   ├── MovieController.java          # Handles movie catalog filtering and ID lookups
│   │   │   ├── ProfileController.java        # Handles profile viewing, updates, and account deletion
│   │   │   └── WatchlistController.java      # Handles adding, removing, and listing saved watchlist items
│   │   ├── dto/
│   │   │   ├── LoginRequest.java             # Data transfer object for login credentials
│   │   │   ├── RegisterRequest.java          # Data transfer object for new user registration
│   │   │   └── UpdateProfileRequest.java     # Data transfer object for profile modifications
│   │   ├── exception/
│   │   │   ├── GlobalExceptionHandler.java   # Centralized @ControllerAdvice for uniform error responses
│   │   │   └── ResourceNotFoundException.java# Custom runtime exception for missing entities (404)
│   │   ├── model/
│   │   │   ├── Movie.java                    # Movie / Series JPA Entity
│   │   │   ├── User.java                     # User account JPA Entity with cascade rules
│   │   │   └── Watchlist.java                # Junction JPA Entity linking Users and Movies
│   │   ├── repository/
│   │   │   ├── MovieRepository.java          # Spring Data JPA repository for movies
│   │   │   ├── UserRepository.java           # Spring Data JPA repository for users
│   │   │   └── WatchlistRepository.java      # Spring Data JPA repository for watchlist mappings
│   │   ├── security/
│   │   │   ├── JwtFilter.java                # OncePerRequestFilter for verifying Bearer tokens
│   │   │   └── JwtUtil.java                  # JWT generation, signature verification, and parsing utility
│   │   └── service/
│   │       ├── AuthService.java              # Business logic for authentication & password hashing
│   │       ├── MovieService.java             # Business logic for movie search and filtering algorithms
│   │       ├── UserService.java              # Business logic for account retrieval, updates, and deletion
│   │       └── WatchlistService.java         # Business logic for managing user watchlist associations
│   └── resources/
│       ├── static/
│       │   ├── index.html                    # Main catalog dashboard & live search interface
│       │   ├── login.html                    # User login portal
│       │   ├── register.html                 # New account registration portal
│       │   ├── profile.html                  # User profile viewing, editing, and account deletion view
│       │   ├── watchlist.html                # Personal saved movies management view
│       │   ├── subscription.html             # Subscription pricing tiers page
│       │   └── script.js                     # Global frontend utility and rendering logic
│       └── application.properties            # Datasource, JPA, and server configurations

```

---

## ⚙️ Prerequisites & Setup Guide

### 1. Requirements

* **Java Development Kit (JDK):** Version 17 or higher
* **Apache Maven:** Version 3.8+
* **Database:** MySQL Server (or default in-memory H2 configuration)

### 2. Clone the Repository

```bash
git clone https://github.com/your-username/my-ott-platform.git
cd my-ott-platform

```

### 3. Configure Database (`application.properties`)

Update `src/main/resources/application.properties` with your database connection properties:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/my_ott_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

server.port=8080

```

### 4. Build and Run the Application

* **Using Maven Wrapper:**
```bash
mvn spring-boot:run

```


* Once started successfully, open your web browser and navigate to:
`http://localhost:8080/login.html`
* **Default Seed User (created automatically on startup):**
* **Email:** `kishore@gmail.com`
* **Password:** *(You can register a new account instantly via the register page)*



---

## 📌 API Endpoints Reference

| Method | Endpoint | Description | Access / Auth |
| --- | --- | --- | --- |
| **POST** | `/api/auth/register` | Register a new user account | Public |
| **POST** | `/api/auth/login` | Authenticate user & return JWT token | Public |
| **GET** | `/movies` | Get catalog (supports query params: `type`, `genre`, `search`) | Public |
| **GET** | `/movies/{id}` | Get specific movie or series details by ID | Public |
| **GET** | `/api/profile` | Retrieve logged-in user profile details | Protected (Bearer Token) |
| **PUT** | `/api/profile` | Update account details (name, email, password) | Protected (Bearer Token) |
| **DELETE** | `/api/profile` | Delete user account & associated watchlists | Protected (Bearer Token) |
| **GET** | `/api/watchlist` | Retrieve user's saved watchlist items | Protected (Bearer Token) |
| **POST** | `/api/watchlist/add/{movieId}` | Add a movie to the user's watchlist | Protected (Bearer Token) |
| **DELETE** | `/api/watchlist/remove/{movieId}` | Remove a movie from the user's watchlist | Protected (Bearer Token) |

---

## 🔒 Security, Validation & Exception Handling

* **Stateless Authentication:** Every protected request validates the incoming JSON Web Token via `JwtFilter`.
* **Input Validation:** Incoming DTO payloads are validated at the controller boundary using Jakarta Validation annotations (`@NotBlank`, `@Email`, `@Size`).
* **Global Error Handling:** All runtime exceptions and custom errors (such as `ResourceNotFoundException`) are caught uniformly by `@ControllerAdvice` (`GlobalExceptionHandler`), returning standardized JSON error responses with appropriate HTTP status codes (`200 OK`, `201 CREATED`, `400 BAD REQUEST`, `401 UNAUTHORIZED`, `404 NOT FOUND`).
