# SchoolApp - Jakarta EE8 REST API with JAX-RS and Hibernate

![Java Version](https://img.shields.io/badge/Java-11-brightgreen.svg)
![Maven](https://img.shields.io/badge/Maven-3.3.2-blue.svg)
![Servlet API](https://img.shields.io/badge/Servlet%20API-4.0.1-orange.svg)
![Jersey](https://img.shields.io/badge/Jersey-2.34-yellow.svg)
![Hibernate](https://img.shields.io/badge/Hibernate-6.1.7.Final-red.svg)
![MySQL Connector](https://img.shields.io/badge/MySQL%20Connector-8.0.32-blue.svg)

This project is a RESTful API for a school management system built using Jakarta EE8, JAX-RS, and Hibernate. It provides endpoints for CRUD operations and follows the DAO (Data Access Object) and DTO (Data Transfer Object) design patterns for managing school-related data.

## Technologies Used

- **Java 11:** The project is written in Java 11, ensuring compatibility and leveraging modern language features.

- **Maven:** Maven is used for project management and dependency resolution.

- **Servlet API 4.0.1:** The Servlet API provides the foundation for handling HTTP requests and responses.

- **Jersey 2.34:** Jersey is used for building RESTful web services with JAX-RS.

- **Hibernate 6.1.7.Final:** Hibernate is employed for object-relational mapping (ORM) to interact with the database.

- **MySQL Connector 8.0.32:** This connector is used to connect the application to the MySQL database.

- **SLF4J and Logback:** SLF4J is the Simple Logging Facade for Java, and Logback is used for logging.

- **HikariCP 5.0.1:** HikariCP provides a high-performance connection pool for database connections.

- **Hibernate HikariCP 6.1.6.Final:** This is a Hibernate-specific integration of HikariCP.

- **Hibernate Validator 6.0.1.Final:** Hibernate Validator is used for data validation.

- **Jersey Media JSON Jackson 2.34:** This library enables JSON support for Jersey.

- **Jersey CDI2 SE 2.34:** It provides CDI (Contexts and Dependency Injection) support for Jersey.

- **Weld SE Core 3.1.9.Final:** Weld is used for CDI in Java SE environments.

- **JUnit 5.9.2:** JUnit is used for testing the application.

## Features

- **CRUD Operations:** Implement Create, Read, Update, and Delete operations for school-related data.

- **DAO and DTO:** Follow the DAO (Data Access Object) and DTO (Data Transfer Object) patterns for structured data access.

- **Service and Controllers:** Organize the application into service and controller layers for a clear separation of concerns.

## Usage

1. Clone this repository:

   ```bash
   git clone https://github.com/your-username/schoolapp-hibernate-jax4.git
