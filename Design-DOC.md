# 🏛️ My OTT Platform - Design Documentation

## 1. Problem Statement

Modern college students and media consumers need a lightweight, high-performance streaming catalog and watchlist platform where they can securely browse movies and series, filter content by genre or type, manage personal profiles, maintain custom watchlists, and subscribe to membership plans.

---

## 2. Layered Architecture

The application follows a standard **3-Tier Layered Architecture** using Spring Boot, ensuring clear separation of concerns:

```
┌───────────────────────────────────────────────┐
│           Presentation Layer                  │
│  (Static HTML / Tailwind CSS / JavaScript)    │
└───────────────────────┬───────────────────────┘
                        │ HTTP / REST APIs (JSON)
┌───────────────────────▼───────────────────────┐
│            Controller Layer                   │
│   (AuthController, MovieController, etc.)     │
└───────────────────────┬───────────────────────┘
                        │ Business Logic & DTOs
┌───────────────────────▼───────────────────────┐
│             Service Layer                     │
│    (AuthService, MovieService, UserService)   │
└───────────────────────┬───────────────────────┘
                        │ JPA / Entity Operations
┌───────────────────────▼───────────────────────┐
│           Data Access Layer (JPA)             │
│   (MovieRepository, UserRepository, etc.)     │
└───────────────────────┬───────────────────────┘
                        │ JDBC / Hibernate
┌───────────────────────▼───────────────────────┐
│           Relational Database                 │
└───────────────────────────────────────────────┘

```

---

## 3. Database Schema & ER Description

The relational database consists of three core entities linked with foreign keys:

```
[USERS] 1 -------- * [WATCHLIST] * -------- 1 [MOVIES]

```

### Tables & Relationships:

1. **`users` Table**: Stores account details.
* Primary Key: `id` (Long)
* Fields: `name`, `email` (Unique), `password` (BCrypt encoded)
* Relation: Has a `OneToMany` relationship with `watchlist` (with cascade delete).


2. **`movies` Table**: Stores movie/series catalog data.
* Primary Key: `id` (Long)
* Fields: `name`, `type` (Movie/Series), `genre`, `year`, `description`, `poster_url`, `rating`.


3. **`watchlist` Table**: Junction table linking users to saved movies.
* Primary Key: `id` (Long)
* Foreign Keys: `user_id` (references `users.id`), `movie_id` (references `movies.id`).



---

## 4. Security & Error Handling Flow

* **Authentication**: Stateless authentication using **JWT (JSON Web Tokens)**. Passwords are securely hashed via **BCryptPasswordEncoder**.
* **Validation**: Input fields are validated using Jakarta Validation annotations (`@NotBlank`, `@Email`, `@Size`).
* **Global Exception Handling**: Custom exceptions (`ResourceNotFoundException`) and validation errors are intercepted centrally by `@ControllerAdvice` (`GlobalExceptionHandler`) to return clean, standardized HTTP status codes (`200`, `201`, `400`, `404`, `401`).
