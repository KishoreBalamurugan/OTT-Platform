# Spring Boot OTT Platform

A full-stack OTT streaming application built to provide media browsing, user authentication, profile management, watchlist tracking, and subscription plan overviews. Designed to allow any developer to set up, configure, and run the complete system independently.

Tech Stack Language: Java Framework: Spring Boot, Spring Data JPA, Spring Security, JWT Database: Cloud MySQL (Aiven) Frontend: HTML, Tailwind CSS, JavaScript Build Tool: Maven

## Database Configuration

Configure your database connection inside `src/main/resources/application.properties` using the following properties:

```properties
spring.application.name=myproject
spring.datasource.url=jdbc:mysql://netflix-ott-db-netflix-ott.l.aivencloud.com:27787/defaultdb?ssl-mode=REQUIRED
spring.datasource.username=avnadmin
spring.datasource.password=YOUR_PASSWORD_HERE

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

```

## How to Build and Run



1. Clone this repository and switch to your feature branch:

```bash
git clone https://github.com/KishoreBalamurugan/JavaSpringBootProject.git
cd ott-platform
git checkout karthikeyan

```

2. Build and run the application using Maven:

```bash
mvn spring-boot:run

```

Alternatively, open the project in IntelliJ IDEA and run `MyprojectApplication.java`.

## Sample Requests



### 1. User Login

```http
POST http://localhost:8080/api/auth/login
Content-Type: application/json

{
  "email": "kishore@gmail.com",
  "password": "1234"
}

```

### 2. Get User Profile (Protected)

```http
GET http://localhost:8080/api/profile
Authorization: Bearer YOUR_JWT_TOKEN

```

### 3. Add Movie to Watchlist (Protected)

```http
POST http://localhost:8080/api/watchlist/add/1
Authorization: Bearer YOUR_JWT_TOKEN

```
