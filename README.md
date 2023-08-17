# SpringbootTest

## Description

This project is a RESTful API built using Spring Boot that provides CRUD operations for managing customers and their addresses.

## Table of Contents

- [Technologies Used](#technologies-used)
- [Features](#features)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Usage](#usage)
- [API Documentation](#api-documentation)
- [Tests](#tests)

## Technologies Used

- Java 11
- Spring Boot Starter 2.7.14
- Spring Boot Starter Data Jpa 2.7.14
- Hibernate
- Spring Boot Devtools 2.7.14
- Postgresql 42.3.8
- Maven

## Features

- Create, read and delete customers and addresses.
- Retrieve customers by city, phone number prefix or Id .

## Getting Started

### Prerequisites

- Java 11
- Maven

### Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/AbdulrahmanShater/SpringbootTest.git
2. Navigate to the project directory:
   ```sh
   cd SpringbootTest
3. Build and run the application:
   ```sh
   mvn spring-boot:run
## Usage
To interact with the API, you can use tools like curl, Postman, or any API client.
### Example API Requests
1. Create New Customer:
    ```sh
    POST /customer
    Request Body: {
    "firstName": "Abdulrahman",
    "lastName": "shater",
    "phoneNumber": "+971503131842",
    "email": "abdulrahmanshatter@gmail.com",
    "addresses": [
        {
            "addressType": "Home",
            "city": "Abu Dhabi",
            "country": "uae",
            "addressLine": "Mecca St"
        }
    ]
    }
2. Retrieve all Customers:
    ```sh
    GET /customer    
3. Retrive a customer by ID:
    ```sh
    GET /customer/{id}
4. Add an address to a customer:
    ```sh
    POST /customer/{id}/address
    Request Body:{
            "addressType": "Home",
            "city": "Abu Dhabi",
            "country": "uae",
            "addressLine": "Mecca St"
        }
    
5. Delete an address from a customer:
    ```sh
    DELETE /customer/{id}/address/{addressId}
### API Documentation
Postman [API documentation](https://github.com/AbdulrahmanShater/SpringbootTest/blob/master/src/postman/README.md) is available in postman directory in project
## Tests
1. tests run :
   ```sh
   mvn test
## Author
Abdulrahman Shater.
## Contact
For inquiries, you can reach me at [abdulrahmanshatter@gmail.com.](mailto:abdulrahmanshatter@gmail.com?subject=[GitHub]%20SpringBootTest/)

