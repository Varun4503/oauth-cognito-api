## Backend (Spring Boot Resource Server)

This service acts as an OAuth 2.0 Resource Server. It validates JWT access tokens issued by AWS Cognito and secures REST APIs using Spring Security.

- Validates JWT using Cognito issuer URI
- Protects endpoints with authentication
- Extracts user claims from token

Frontend Repo: https://github.com/Varun4503/oauth-cognito-react-client

## Getting Started (Run Locally)

Follow these steps to run the backend locally after cloning/forking the repository.

---

### 1. Clone the Repository

```bash
git clone https://github.com/Varun4503/oauth-cognito-api.git
cd oauth-cognito-api
```

---

### 2. Configure Environment

Create a file:

```text
src/main/resources/application.properties
```

You can copy from:

```text
src/main/resources/application-example.properties
```

---

### 3. Update Configuration

Fill in the required values:

```properties
# ==============================
# AWS Cognito Configuration
# ==============================
spring.security.oauth2.resourceserver.jwt.issuer-uri=YOUR_COGNITO_ISSUER_URI
# Example:
# https://cognito-idp.<region>.amazonaws.com/<user_pool_id>

# ==============================
# Database Configuration
# ==============================
spring.datasource.url=YOUR_DB_URL
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
```

---

### 4. Setup Database

You can use any PostgreSQL database (local or cloud).

Recommended options:

* Local PostgreSQL
* Supabase (free tier)

Make sure the database is accessible and credentials are correct.

---

### 5. Run the Application

Using Maven:

```bash
./mvnw spring-boot:run
```

Or:

```bash
mvn spring-boot:run
```

---

### 6. Test the API

* Obtain a JWT access token from AWS Cognito
* Call secured endpoint:

```bash
GET http://localhost:8080/api/test
Authorization: Bearer <your_token>
```

---

## 🔐 How Authentication Works

1. User logs in via AWS Cognito
2. Frontend sends JWT (access_token) to backend
3. Backend validates JWT
4. User is synced to database (if not exists)
5. Role and permissions are assigned
6. Access is granted based on authorities

---

## ⚠️ Notes

* `application.properties` is ignored (contains sensitive data)
* Use `application-example.properties` as reference
* First request creates user automatically in DB
* Ensure correct Cognito issuer URI is used

---

## 🛠 Requirements

* Java 21
* Maven
* PostgreSQL (or compatible DB)
* AWS Cognito setup

