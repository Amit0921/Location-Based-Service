📌 Location Based Service API

📖 Overview

Location Based Service (LBS) API is a RESTful backend service that provides spatial operations including:
* Create geo-tagged places
* Search nearby places within radius
* Find nearest place
* Calculate distance between two coordinates

Built using:
* Spring Boot
* Spring Data JPA
* PostGIS
* JTS Geometry
* Swagger

🚀 Tech Stack
- Java 17+
- Spring Boot
- PostgreSQL + PostGIS
- Hibernate Spatial
- JTS (Java Topology Suite)
- Swagger

📦 Setup Instructions

1️⃣ Clone Repository

    git clone <repository-url>
    cd location-based-service

2️⃣ Setup Database

Install:
* PostgreSQL
* PostGIS extension

Create database Enable PostGIS and Create Tables:

    CREATE DATABASE spatialdb;
    CREATE EXTENSION postgis;

    CREATE TABLE place (
        id BIGSERIAL PRIMARY KEY,
        name VARCHAR(255) NOT NULL,
        type VARCHAR(100),
        location GEOGRAPHY(Point, 4326) NOT NULL
    );

    CREATE INDEX idx_place_location
      ON place
      USING GIST (location);
      Enable PostGIS:

3️⃣ Configure application.yml

    spring:
      datasource:
        url: jdbc:postgresql://localhost:5432/lbs
        username: postgres
        password: yourpassword

4️⃣ Run Application

    mvn spring-boot:run
Application runs at:

    http://localhost:8080
Swagger UI:

    http://localhost:8080/api/v1/swagger-ui/index.html
📌 API Endpoints

    Base URL: /api/v1
1️⃣ Create Place

    POST /api/v1/places
    Request Body
    {
      "name": "Apollo Hospital",
      "type": "Hospital",
      "latitude": 12.9716,
      "longitude": 77.5946
    }

2️⃣ Find Nearby Places

    GET /api/v1/places/nearby
    GET /api/v1/places/nearby?lat=12.97&lon=77.59&radius=2000
3️⃣ Find Nearest Place

    GET /api/v1/places/nearest
    /api/v1/places/nearest?lat=12.97&lon=77.59

4️⃣ Calculate Distance

    GET /api/v1/places/distance
    /api/v1/places/distance?lat1=12.97&lon1=77.59&lat2=13.01&lon2=77.62

📌 Architecture Overview
* Controller → Service → Repository → PostgreSQL/PostGIS
* Controllers handle HTTP requests
* Service contains business logic
* Repository executes spatial queries
* Database handles spatial indexing
* Exceptions and Errors are handled Globally
