Spring Boot OTT Platform
This repository contains a full-stack OTT streaming platform application built using Spring Boot and MySQL. The system provides user authentication, a movie and series catalog with search and genre filters, subscription plan overviews, user profile management, and a watchlist backend.

Key Features
Secure Authentication: JWT-based login mechanism that verifies user sessions and securely stores session tokens in browser storage.

User Profile Management: Retrieves and displays logged-in user account details (ID, name, and email) by decoding the authorization JWT token.

Home Dashboard and Catalog:

Dynamic browsing for movies and series.

Live search functionality and category filters for "All", "Movies", and "Series".

Integrated static rating scores and poster previews for titles.

Subscription Plans: A dedicated pricing layout outlining available subscription tiers (Free, Standard, and Premium) without payment gateway integrations.

Watchlist System: Backend support enabling users to save and retrieve their preferred titles.

Technology Stack:

Backend: Java, Spring Boot.

Frontend: HTML5, CSS.

Database: Cloud MySQL 

Build Tool: Maven

Version Control: Git & GitHub

Installation:

Prerequisites
Ensure the following software is installed on your local machine:

Java Development Kit (JDK) 21 or higher

Maven

Git

IntelliJ IDEA (or any preferred Java IDE)

Setup:

Clone the Repository
```
git clone https://github.com/KishoreBalamurugan/JavaSpringBootProject.git 
cd ott-platform
```

Switch to Your Feature Branch
```
git checkout karthikeyan
```

Configure Database Settings
```
spring.application.name=myproject
spring.datasource.url=jdbc:mysql://netflix-ott-db-netflix-ott.l.aivencloud.com:27787/defaultdb?ssl-mode=REQUIRED
spring.datasource.username=avnadmin
spring.datasource.password=YOUR_PASSWORD_HERE

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
Run the Application
```
mvn spring-boot:run
```
