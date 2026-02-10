# Question 1 Library Book Management API

Create a RESTful API for a simple library book management system.

## Model and DTO

This is the Model Structure and also the Request Body for POST request :

```json
{
    "id": 1200,
    "title": "Bla Bla Bla",
    "author": "Tester",
    "ISNB": "TES12",
    "publicationYear": 1999
}
```

## Endpoints for the Library Book Management (`/api/books`)

### 1. **GET  `/`** (Get all books) : 

![GET all books](image.png)

### 2. **GET `/{id}`** (Get specific book):

![GET By Id](image-1.png)

### 3. **GET `/search?title={title}`** (Get bassed on the title)

![GET By title](image-2.png)

### 5. **POST `/`** (Add new book)

![Add new Book](image-3.png)

### 7. **DELETE `/{id}`** (Remove book)

![REmove book](image-4.png)


# THE END 