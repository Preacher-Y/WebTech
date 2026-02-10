# Question 5 Task Management API

Create a simple task/to-do list API.

## Model and DTO

This is the Model Structure and also the Request Body for POST request :

```json
{
    "taskId":11,
    "title":"Testing",
    "description":"Test each Endpoint",
    "completed":false,
    "priority":"LOW",
    "dueDate":"2026-02-03"
}
```

## Endpoints for the Task Management (`/api/tasks`)

### 1. **GET  `/`** (Get all Tasks) : 

![GET all tasks](image.png)

### 2. **GET `/{id}`** (Get specific task):

![GET Tasks by ID](image-1.png)

### 3. **GET `/status?completed={true/false}`** (Get bassed on the completeness)

![By completeness](image-2.png)

### 4. **GET `/priority/{priority}`** (Get tasks by priority):

![GET By priority](image-3.png)

### 5. **POST `/`** (Add new task)

![Add new Task](image-4.png)

### 6. **PUT `/{id}`** (Update TAsk)

![Update Task](image-5.png)

### 6. **PUT `/{id}/complete`** (Change Task Status)

![Complete Task](image-6.png)

### 7. **DELETE `/{id}`** (Remove Task)

![Remove the Task](image-7.png)


# THE END 