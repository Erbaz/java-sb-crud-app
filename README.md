### 📌 Final Assignment
You are required to develop a Spring Boot REST API that includes:

*1️⃣ Entities:*
- Customer
- Item
- Order

*2️⃣ Functional Requirements:* \
Implement full CRUD operations for all entities
Establish proper relationships (e.g., Order should be linked to Customer and Items)
Apply proper validation and exception handling
Follow clean code and layered architecture (Controller → Service → Repository) \

*3️⃣ Security Requirement:*\
Secure the APIs using Spring Security
Implement basic authentication (or JWT if you feel confident)
Protect order-related endpoints appropriately



### Setup Pre-requisites
- Java OpenJDK 21 should be installed in system
- Docker VM or Rancher Desktop should be available
- This project is to be run via Testcontainers. So make sure you make the following changes to the Intellij configuration for the project:
  - run `docker context list` and copy the socket path for the docker context in use
  - paste in configuration with `DOCKER_HOST=YOUR_SOCKET_PATH_HERE;TESTCONTAINERS_RYUK_DISABLED=true`
  - add the following values to the application.properties:
    ```
    spring.application.name=assignment
    spring.jpa.hibernate.ddl-auto=update
    
    admin.username=admin
    admin.password=admin
    jwt.secret=mysupersecuresecretkeythatisatleast32characterslong
    ```
