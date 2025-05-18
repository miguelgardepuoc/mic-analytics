# Antharos Analytics Service

## Overview

The Analytics service is a microservice within the Antharos HR platform ecosystem. It is designed to process Antharos system events and generate functional metrics. This service is built following a hexagonal architecture pattern.

## Technology Stack

- **Framework**: Spring Boot 3.4
- **Build Tool**: Maven
- **Language**: Java 21
- **Architecture**: Hexagonal Architecture
- **Database**: PostgreSQL
- **Event Bus**: Azure Service Bus

## Domain Events

The service consumes the following domain events:
- `EmployeeHired`
- `EmployeeOnLeave`
- `EmployeeTerminated`
- `EmployeeMarkedAsInactive`

## Getting Started

### Prerequisites

- JDK 21+
- Maven 3.9+
- PostgreSQL 14+
- Docker & Docker Compose

### Installation

```bash
# Clone the repository
git clone https://github.com/miguelgardepuoc/mic-analytics.git
cd mic-analytics

# Build the project
./mvnw clean install -U
```

### Running Locally

```bash
# Start all dependencies with Docker Compose
docker-compose up --build -d --remove-orphans
```

```bash
# Run the service
mvn spring-boot:run
```

The service will be available at `http://localhost:8082/analytics`.
APIs documentation will be available at `http://localhost:8082/analytics/swagger-ui/index.html`.

## Format code

Code is formatted using spotless-maven-plugin applying google java format:
```bash
mvn spotless:apply
```

## Code coverage

Code coverage is measured using JaCoCo. To generate the report:
```bash
mvn clean verify
```
This command will execute all tests and generate JaCoCo reports. An aggregated report is generated under the `aggregate-report` module.

To view the full aggregated coverage report, open the following file in your browser:
```pgsql
aggregate-report/target/site/jacoco-aggregate/index.html
```
This report shows consolidated coverage data across the entire repository.
