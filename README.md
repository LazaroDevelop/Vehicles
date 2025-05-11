## Vehicle API

**Description**
A RESTful API for registering and managing vehicle operations, built with Java 21 and Spring Boot 3.4.5.

---

### 📋 Technologies

* **Language & Framework:** Java 21, Spring Boot 3.4.5
* **Databases:** PostgreSQL (production), H2 (in-memory for tests)
* **Persistence:** Spring Data JPA
* **Caching:** Caffeine Cache
* **API Style:** Spring HATEOAS
* **Mapping:** MapStruct
* **Annotations:** JetBrains (`@NotNull`, `@Nullable`, etc.)
* **Native Image:** GraalVM for optimized, low-footprint builds
* **Documentation:** Swagger/OpenAPI v3
* **Containerization:** Docker, Docker Compose

---

### 🚀 Getting Started

#### Prerequisites

* Java 21
* GraalVM (for native-image builds)
* Docker & Docker Compose
* Maven

#### Clone the Repository

```bash
[git clone https://github.com/yourusername/vehicle-api.git
cd vehicle-api](https://github.com/LazaroDevelop/Vehicles)
```

#### Configuration

Copy `application.yml.example` to `application.yml` and adjust database credentials:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/vehicles_db
    username: postgres
    password: yourpassword
  jpa:
    hibernate:
      ddl-auto: update
```

#### Running with Docker Compose

```bash
docker-compose up --build
```

This will start the API along with a PostgreSQL container.

---

### 🗄️ Database

* **PostgreSQL**: Used in production and via Docker Compose
* **H2**: In-memory DB for fast local testing

Scripts are automatically run at startup to initialize schemas and sample data.

---

### 🛠️ Caching

Caffeine cache is configured to cache frequent queries for improved performance. Default TTL and maximum size can be customized in `application.yml`.

---

### 🌐 HATEOAS

All resource responses include HATEOAS links to navigate through API resources.

---

### 🔄 Mapping

MapStruct is used to map between Entity and DTO classes for cleaner, boilerplate-free code.

---

### 🏎️ GraalVM Native Image

To build a native executable:

```bash
mvn -Pnative clean package
```

Run via:

```bash
./target/vehicle-api
```

---

### 📄 API Documentation (Swagger)

Access the Swagger UI at `http://localhost:8081/swagger-ui.html`.

Placeholder for Swagger UI screenshot:

![Swagger UI](docs/swagger-screenshot.png)

---

### 🐳 Docker

* **Dockerfile** included for container image creation.
* **Docker Compose** service definitions for API and database.

Build image locally:

```bash
docker build -t vehicle-api:latest .
```

---

### ⚙️ Usage

Once the application is up, you can perform standard CRUD operations on `/api/vehicles`:

* `GET /api/vehicles/all` - List all vehicles
* `GET /api/vehicles/{id}` - Get vehicle by ID
* `POST /api/vehicles/create` - Create new vehicle
* `PUT /api/vehicles/update/{id}` - Update existing vehicle
* `DELETE /api/vehicles/delete/{id}` - Remove a vehicle

---

### 🤝 Contributing

Contributions are welcome! Please open issues and pull requests.
