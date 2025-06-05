
# STO - Car Repair Request Management System

## 🎯 Project Goal

Develop a system for managing car repair requests at a service station (STO), including:

- Creating and tracking repair requests
- Managing repair statuses
- Notifying clients
- Maintaining a history of status changes

## 🔧 Technology Stack

- **Backend**: Spring Boot 3, Java 21
- **Database**: PostgreSQL 16
- **Message Broker**: Apache Kafka
- **API Documentation**: OpenAPI 3 (Swagger)
- **Database Migrations**: Liquibase
- **Containerization**: Docker

## 🚀 Quick Start

1. **Launch with Docker Compose**:
   ```bash
   docker-compose up -d
   ```

2. **Access the Application**:
   - URL: `http://localhost:8080`

## 📚 API Documentation

After starting the application, access the API documentation at:

- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **OpenAPI Specification**: `http://localhost:8080/v3/api-docs`

## 🛠️ Core Components

- **RepairRequest**: Represents a car repair request
- **StatusHistory**: Tracks changes to request statuses
- **Kafka**: Handles events for status changes and client notifications

### Workflow

1. Client submits a request → Status: `CREATED`
2. Mechanic accepts the request → Status: `IN_PROCESSING`
3. Repair starts → Status: `IN_REPAIR`
4. Repair completes → Status: `COMPLETED`
5. Client is notified → Status: `CLIENT_NOTIFIED`

## 📡 Kafka Integration

The system uses two Kafka topics:

- **`status-changed-topic`**: Captures events for status changes
- **`notification-topic`**: Manages client notification events

## 🐳 Running Locally

Ensure Docker and Docker Compose are installed. Then, run:

```bash
docker-compose up -d
```

Check the application logs:

```bash
docker-compose logs sto
```

Access Swagger UI to test endpoints: `http://localhost:8080/swagger-ui.html`

## 🛠️ Development Setup

1. Install JDK 21 and Maven.
2. Run locally without Docker:
   ```bash
   mvn spring-boot:run -Dspring.profiles.active=local
   ```
3. Verify database migrations and Kafka topics:
   ```bash
   docker exec -it sto-db psql -U postgres -d sto -c "\dt"
   docker exec -it kafka kafka-topics --bootstrap-server localhost:9092 --list
   ```