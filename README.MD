# Invoice API Spring Boot Application

This is a Spring Boot application for companies using this service to generate invoices and payment links which they send to their customers using Spring Data JPA and Spring Web.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java Development Kit (JDK) installed (version 21 or higher)
- Maven installed (version 3.0.0 or higher)
- Your favorite IDE (IntelliJ IDEA, Eclipse, etc.) with Spring Boot support

## Getting Started

Follow these steps to run the Spring Boot application:

1. **Clone the repository:**

    ```bash
    git clone https://github.com/saviourumoeka/invoice-api.git
    ```

2. **Navigate to the project directory:**

    ```bash
    cd invoice-api
    ```

3. **Build the project:**

    ```bash
    mvn clean install
    ```

4. **Run the application:**

    ```bash
    mvn spring-boot:run
    ```

   The application will start, and you can access it at [http://localhost:8080/api/v1](http://localhost:8080).

5. **Access the H2 Database Console:**

   The H2 database console is available at [http://localhost:8080/api/v1/h2-console](http://localhost:8080/api/v1/h2-console). Use the following details to log in:

    - JDBC URL: `jdbc:h2:mem:testdb`
    - Username: `sa`
    - Password: (Leave it blank)

   Please note that the H2 database is an in-memory database used for development purposes.

6. Swagger Documentation UI at http://localhost:8080/api/v1/swagger-ui/index.html
         production: https://invoice-hrded74ysa-uc.a.run.app//api/v1/swagger-ui/index.html
   
   

## Additional Information

- The project uses Lombok for reducing boilerplate code.
- The documentation is generated using Asciidoctor and Spring REST Docs.