# PhaseZero Catalog Service

A simple Spring Boot REST API for managing catalog items.  
This project demonstrates layered architecture, validation, and standard REST API patterns.

---

## How to Build and Run

Run the application using:

```bash
mvn spring-boot:run
The API will be available at:

http://localhost:8080

Endpoints Overview
Method	Endpoint	Description
GET	/catalog/getAll	Fetch all catalog items
GET	/catalog/{id}	Get item by ID
POST	/catalog/create	Create a new catalog item
PUT	/catalog/{id}	Update an existing item
DELETE	/catalog/{id}	Delete an item by ID

Example Requests and Responses
1. Create Item
Endpoint: POST /catalog/create

Request:

json
Copy code
{
  "name": "Wireless Mouse",
  "details": "Ergonomic mouse",
  "price": 699.0,
  "quantity": 10,
  "partNumber": "MOU-123"
}
Response:

json
Copy code
{
  "id": 1,
  "name": "Wireless Mouse",
  "details": "Ergonomic mouse",
  "price": 699.0,
  "quantity": 10,
  "partNumber": "MOU-123"
}
2. Get All Items
Endpoint: GET /catalog/getAll

Response:

json
Copy code
[
  {
    "id": 1,
    "name": "Wireless Mouse",
    "details": "Ergonomic mouse",
    "price": 699.0,
    "quantity": 10,
    "partNumber": "MOU-123"
  }
]
3. Update Item
Endpoint: PUT /catalog/1

Request:

json
Copy code
{
  "name": "Wireless Mouse Updated",
  "details": "Updated ergonomic design",
  "price": 799.0,
  "quantity": 5,
  "partNumber": "MOU-123"
}
Response:

json
Copy code
{
  "id": 1,
  "name": "Wireless Mouse Updated",
  "details": "Updated ergonomic design",
  "price": 799.0,
  "quantity": 5,
  "partNumber": "MOU-123"
}
Validations and Error Handling
partNumber must be unique

name, price, quantity, partNumber are required

price must be zero or positive

quantity must be zero or positive

Custom exception handling returns clear error messages

Example error response for invalid ID:

json
Copy code
{
  "error": "Catalog item not found with ID: 20"
}
Project Design
This project follows a clean layered architecture:

vbnet
Copy code
src/main/java/com/catalog
|
├── controller       Handles API requests
├── service          Contains business logic
├── repository       JPA repository interfaces
├── entity           JPA entity classes
└── exception        Custom exceptions and global handlers
Tech Stack
Java 17
Spring Boot 3
Spring Data JPA
Hibernate
Maven
Postgres

Assumptions and Limitations

No authentication or authorization included
No pagination on item listing
partNumber must be unique for each item
Basic CRUD operations only
