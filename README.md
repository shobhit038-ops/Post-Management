# Blog Management System

A RESTful Blog Management backend built with **Spring Boot 3.5.6** and **Java 21**. It provides secure API endpoints for managing blog content, with JWT-based authentication, OAuth2 social login, and full OpenAPI documentation.

---

## 🚀 Tech Stack

| Layer | Technology |
|---|---|
| Framework | Spring Boot 3.5.6 |
| Language | Java 21 |
| Security | Spring Security, JWT (jjwt 0.11.5)|
| Persistence | Spring Data JPA + PostgreSQL |
| Email | Spring Boot Mail |
| API Docs | SpringDoc OpenAPI (Swagger UI) |
| Utilities | Lombok |
| Build Tool | Maven |

---

## 📋 Prerequisites

- **Java 21** or higher
- **Maven 3.8+** (or use the included `./mvnw` wrapper)
- **PostgreSQL** database (running and accessible)
- An SMTP server or mail service for email features (e.g., Gmail SMTP

---

## ⚙️ Configuration

Create or update `src/main/resources/application.properties` (or `application.yml`) with the following:

```properties
# Server
server.port=8080

# Database

spring.datasource.url=jdbc:postgresql://aws-1-ap-south-1.pooler.supabase.com:6543/postgres
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.hikari.maximum-pool-size=5
spring.datasource.hikari.connection-timeout=30000
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.datasource.hikari.data-source-properties.cachePrepStmts=false
spring.datasource.hikari.data-source-properties.useServerPrepStmts=false

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
logging.level.org.springframework.security=DEBUG
management.endpoints.web.exposure.include=health,info,metrics,beans,env,loggers
management.endpoint.health.show-details=always
# JWT
jwt.secret=your_jwt_secret_key
jwt.expiration=86400000

# Mail
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your_email@gmail.com
spring.mail.password=your_email_password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

> ⚠️ **Note:** The package name `com.example.blog-management` is invalid in Java. This project uses `com.example.blog_management` instead.

---

## 🏃 Running the Application

### Using Maven Wrapper (recommended)

```bash
# On Linux / macOS
./mvnw spring-boot:run

# On Windows
mvnw.cmd spring-boot:run
```

### Using Maven directly

```bash
mvn spring-boot:run
```

### Building a JAR

```bash
./mvnw clean package
java -jar target/blog-management-0.0.1-SNAPSHOT.jar
```

---

## 📖 API Documentation

Once the application is running, Swagger UI is available at:

```
https://post-management-production.up.railway.app/swagger-ui/index.html
```

The OpenAPI JSON spec is accessible at:

```
https://post-management-production.up.railway.app/swagger-ui/api-docs
```

---

## 🔐 Authentication

This project supports THAT uthentication mechanisms:

1. **JWT Authentication** — Register/login with email and password to receive a JWT token. Pass it in the `Authorization` header as `Bearer <token>` for all protected endpoints.

## 🗂️ Project Structure

```
blog-management/
├── src/
│   ├── main/
│   │   ├── java/com/example/blog_management/
│   │   │   ├── controller/       # REST controllers
│   │   │   ├── service/          # Business logic
│   │   │   ├── repository/       # JPA repositories
│   │   │   ├── model/            # Entity classes
│   │   │   ├── dto/              # Data Transfer Objects
│   │   │   ├── security/         # JWT & Security config
│   │   │   └── BlogManagementApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── pom.xml
├── mvnw
└── mvnw.cmd
```

---

## 🧪 Running Tests

```bash
./mvnw test
```

Tests use `spring-boot-starter-test` and `spring-security-test`.

---

## 📦 Dependencies Overview

| Dependency | Purpose |
|---|---|
| `spring-boot-starter-web` | REST API support |
| `spring-boot-starter-security` | Authentication & authorization |
| `spring-boot-starter-oauth2-client` | Social login (Google, GitHub, etc.) |
| `spring-boot-starter-data-jpa` | Database access via JPA |
| `spring-boot-starter-mail` | Email notifications |
| `jjwt-api / jjwt-impl / jjwt-jackson` | JWT token generation & validation |
| `postgresql` | PostgreSQL JDBC driver |
| `lombok` | Boilerplate code reduction |
| `springdoc-openapi-starter-webmvc-ui` | Swagger UI & OpenAPI docs |

---

## 📚 Reference Documentation

- [Spring Boot Docs](https://docs.spring.io/spring-boot/3.5.6/)
- [Spring Web](https://docs.spring.io/spring-boot/3.5.6/reference/web/servlet.html)
- [Spring Security](https://docs.spring.io/spring-boot/3.5.6/reference/web/spring-security.html)
- [Spring Data JPA](https://docs.spring.io/spring-boot/3.5.6/reference/data/sql.html)
- [OAuth2 Client](https://docs.spring.io/spring-boot/3.5.6/reference/web/spring-security.html#web.security.oauth2.client)
- [Apache Maven](https://maven.apache.org/guides/index.html)

---

## 📝 License

This project is for demo/learning purposes. No license is explicitly defined.
