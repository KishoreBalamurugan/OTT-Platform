Here is a brief, clean API documentation formatted for your My OTT Platform project, modeled after standard developer-friendly API references:

---

#  My OTT Platform - API Documentation

Base URL: `http://localhost:8080`

##  Authentication Endpoints

All protected endpoints require a Bearer Token passed in the header:

`Authorization: Bearer <JWT_TOKEN>`

### 1. Register User

* **URL:** `/api/auth/register`
* **Method:** `POST`
* Request Body (JSON):```json
{
"name": "John Doe",
"email": "john@example.com",
"password": "password123"
}

```
* **Responses:**
  * `201 CREATED`: `"Registration successful"`
  * `400 BAD_REQUEST`: Validation error or email already exists.

### 2. Login User
* **URL:** `/api/auth/login`
* **Method:** `POST`
* **Request Body (JSON):```json
{
  "email": "john@example.com",
  "password": "password123"
}

```

* **Responses:**
* `200 OK`: Returns JWT token string.
* `401 UNAUTHORIZED`: Invalid email or password.



---

## 👤 Profile Endpoints (Protected)

### 3. Get User Profile

* **URL:** `/api/profile`
* **Method:** `GET`
* **Responses:**
* `200 OK`: Returns User JSON object (`id`, `name`, `email`).
* `401 UNAUTHORIZED`: Missing or invalid token.



### 4. Update User Profile

* **URL:** `/api/profile`
* **Method:** `PUT`
* Request Body (JSON):```json
{
"name": "John Updated",
"email": "john.new@example.com",
"password": "newpassword123"
}

```
* **Responses:**
  * `200 OK`: Returns updated User JSON object.

### 5. Delete Account
* **URL:** `/api/profile`
* **Method:** `DELETE`
* **Responses:**
  * `200 OK`: `"Account deleted successfully"`

---

## 🎥 Movie Catalog Endpoints

### 6. Get Movies / Series
* **URL:** `/movies`
* **Method:** `GET`
* **Query Parameters (Optional):**
  * `type`: Filter by `"Movie"` or `"Series"`
  * `genre`: Filter by genre (e.g., `"Sci-Fi"`, `"Action"`)
  * `search`: Search movie name (e.g., `"Inception"`)
* **Responses:**
  * `200 OK`: Returns JSON array of movies.

### 7. Get Movie by ID
* **URL:** `/movies/{id}`
* **Method:** `GET`
* **Responses:**
  * `200 OK`: Returns Movie JSON object.
  * `404 NOT_FOUND`: Movie not found.

---

## Watchlist Endpoints (Protected)

### 8. Get User Watchlist
* **URL:** `/api/watchlist`
* **Method:** `GET`
* **Responses:**
  * `200 OK`: Returns JSON list of watchlist items.

### 9. Add Movie to Watchlist
* **URL:** `/api/watchlist/add/{movieId}`
* **Method:** `POST`
* **Responses:**
  * `200 OK`: `"Movie added to watchlist successfully!"` or `"Movie is already in your watchlist!"`
  * `404 NOT_FOUND`: Movie or User not found.

### 10. Remove Movie from Watchlist
* **URL:** `/api/watchlist/remove/{movieId}`
* **Method:** `DELETE`
* **Responses:**
  * `200 OK`: `"Movie removed from watchlist successfully!"`
  * `404 NOT_FOUND`: Item not found in watchlist.

```
