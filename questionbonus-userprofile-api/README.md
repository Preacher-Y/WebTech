# Bonus Question User Profile API

Create a comprehensive user profile management API.

## Model and DTO

This is the Model Structure and also the Request Body for POST request :

```json
{
  "userId":"11",
  "username": "preacher_y",
  "email": "preacher.y@example.com",
  "fullName": "Preacher Y",
  "age": 22,
  "country": "RW",
  "bio": "Senior Full-Stack Developer",
  "active": true
}
```

## Endpoints for the User Profile (`/api/menu`)

### 1. **GET  `/`** (Get all users) : 

![GET all Users](image.png)

### 2. **GET `/{id}`** (Get specific user):

![GET By ID](image-1.png)

### 3. **GET `/search?country={country}`** (Search users by country)

![search by country](image-2.png)

### 4. **POST `/`** (Add new user)

![add new User](image-3.png)

### 5. **PUT `/{id}`** (Update user)

![Update user](image-4.png)

### 6. **PUT `/{id}/change-status`** (Toggle user's status)

*Note:* Since its a toggle, i choose to first get the previous value of the status and the do its opposite.

![Toggle status](image-5.png)

### 7. **DELETE `/{id}`** (Remove user)

![remove user](image-6.png)


# THE END 