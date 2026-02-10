# Question 1 Student Registration API

Build a REST API for student registration and information management.

## Model and DTO

This is the Model Structure and also the Request Body for POST request :

```json
{
    "studentId": 1,
    "firstName": "Tester",
    "lastName": "1",
    "email": "tester@auca.ac.rw",
    "major": "QA Tester",
    "gpa": 3.59
}
```

## Endpoints for the Student registation (`/api/students`)

### 1. **GET  `/`** (Get all Students) : 

![Get All](image.png)

### 2. **GET `/{id}`** (Get specific Student):

![GEtByID](image-1.png)

### 3. **GET `/major/{major}`** (Get Students by major):

![Get by major](image-2.png)

### 4. **GET `/filter?gpa={gpa}`** (Search Students by gpa)

![filter by gpa](image-3.png)

### 5. **POST `/`** (Add new Student)

![Add new Student](image-4.png)

### 6. **PUT `/{id}`** (Update Student)

![Update Student](image-5.png)

# THE END 