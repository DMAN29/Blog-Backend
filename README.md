# Blog Website (Backend-Only)

## Overview
The Blog Website backend is a RESTful API developed using Spring Boot. It supports user authentication, blog management, and role-based access control, providing a secure and efficient blogging platform.

## Technologies Used
- **Backend:** Spring Boot, MySQL
- **Security:** JWT-based authentication, Role-based access control

## Features
- User registration and authentication (JWT-based)
- CRUD operations for blog posts
- Role-based access control (Admin/User)
- Secure API endpoints

## Repository
- **Backend Repository:** [Blog-Backend](https://github.com/DMAN29/Blog-Backend)

## Setup Instructions
### Prerequisites
- Java 17+
- Maven
- MySQL database

### Installation Steps
1. Clone the repository:
   ```sh
   git clone https://github.com/DMAN29/Blog-Backend.git
   ```
2. Navigate to the project directory:
   ```sh
   cd Blog-Backend
   ```
3. Configure the database connection in `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/blog_db
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   ```
4. Build and run the backend application:
   ```sh
   mvn spring-boot:run
   ```

## API Endpoints
| Method | Endpoint          | Description                     |
|--------|------------------|---------------------------------|
| POST   | /auth/register   | User registration              |
| POST   | /auth/login      | User login, returns JWT token  |
| GET    | /blogs           | Get all blogs                  |
| GET    | /blogs/{id}      | Get blog by ID                 |
| POST   | /blogs           | Create a new blog (Auth required) |
| PUT    | /blogs/{id}      | Update blog (Auth required)    |
| DELETE | /blogs/{id}      | Delete blog (Admin only)       |

## Contribution
Feel free to fork the repository and submit pull requests to improve the project.

## License
This project is licensed under the MIT License.

