# Monitor Sensors CRUD Application

This is a CRUD application for managing monitor sensors using Java, Spring Boot, Spring Security, and Hibernate. The application allows users to perform CRUD operations on sensor data stored in a relational database.

## Features

- Create, Read, Update, and Delete (CRUD) operations for sensors
- Authentication and authorization using Spring Security
- Role-based access control (Administrator and Viewer roles)
- Search functionality for sensors by name and model

## Sensor Entity

The sensor entity includes the following fields:
- Name (required, text, 3-30 characters)
- Model (required, text, up to 15 characters)
- Range (required, numeric, positive integers, `from` must be less than `to`)
- Type (required, predefined list: Pressure, Voltage, Temperature, Humidity)
- Unit (predefined list: bar, voltage, °С, %)
- Location (text, up to 40 characters)
- Description (text, up to 200 characters)

## Predefined Users

- `admin` (role: Administrator)
- `user` (role: Viewer)

## Running the Application

### Prerequisites

- Java 17
- Maven

### Steps

1. Clone the repository:
    ```sh
    git clone https://github.com/NastyaVusik/monitor-sensors.git
    cd monitor-sensors
    ```

2. Build and run the application locally:
    ```sh
    mvn clean install
    mvn spring-boot:run
    ```

3. Access the application:
    - The application will be running at `http://localhost:8080/monitor-sensors`
    - Use the following credentials to log in:
        - Administrator: `admin` / `admin`
        - Viewer: `user` / `user`

### Building and Running the Docker Image

1. Build the Docker image using a two-stage build:
    ```sh
    docker build -t monitor-sensors -f docker/DockerFile .
    ```

2. Run the Docker container:
    ```sh
    docker run -p 8080:8080 monitor-sensors
    ```

3. Access the application:
    - The application will be running at `http://localhost:8080/monitor-sensors`
    - Use the following credentials to log in:
        - Administrator: `admin` / `admin`
        - Viewer: `user` / `user`

### API Endpoints

- `GET /monitor-sensors/sensors` - Get all sensors (Admin and Viewer)
- `GET /monitor-sensors/sensors/{id}` - Get sensor by ID (Admin only)
- `POST /monitor-sensors/sensors` - Create a new sensor (Admin only)
- `PUT /monitor-sensors/sensors/{id}` - Update a sensor (Admin only)
- `DELETE /monitor-sensors/sensors/{id}` - Delete a sensor (Admin only)
- `GET /monitor-sensors/sensors/search?search={query}` - Search sensors by name or model (Admin and Viewer)

### H2 Database Console

- Access the H2 database console at `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: `password`

### Swagger UI

- Access the Swagger UI at `http://localhost:8080/monitor-sensors/swagger-ui/index.html`

### Postman Collection

- A Postman collection is available to test the API endpoints. You can import the collection from the postman_collection.json file.

## Dependencies

- Spring Boot
- Spring Data JPA
- H2 Database (for testing)
- Lombok
- Springdoc OpenAPI
- Liquibase
- MapStruct
- Spring Security

## License

This project is licensed under the MIT License.