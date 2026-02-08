# Question 4 E-Commerce Product API

Create an API for a E-commerce product catalog.

## Model and DTO

This is the Model Structure and also the Request Body for POST request :

```json
{
    "productId": 11,
    "name": "iPhone 17",
    "description": "Apple smartphone with A25 Bionic chip and Titanium casing",
    "price": 1999.99,
    "category": "Electronics",
    "stockQuantity": 17,
    "brand": "Apple"
}
```

## Endpoints for the Restaurant (`/api/products`)

### 1. **GET  `/?page={page}&limit={limit}`** (Get all products) : 

*Note:* Without Pagination:

![Without pagination](image.png)

*Note:* With Pagination:

![With Pagination](image-1.png)

### 2. **GET `/{id}`** (Get specific Product by ID):

![Get product by ID](image-2.png)

### 3. **GET `/category/{category}`** (Get Products by category):


![Get product by Category](image-3.png)

### 4. **GET `/brand/{brand}`** (Get Product by brand):

![GEt Product by brand](image-4.png)

### 5. **GET `/search?keyword={keyword}`** (Search Products by name or description)

![Search By KeyWord in name and desc](image-5.png)

## 6. **GET `/price-range?min={min}&max={min}`** (Get Products based on price)

![Get product based on price](image-6.png)

## 7. **GET `/in-stock`** (Get Products based on stock )

![in-stock](image-7.png)

### 8. **POST `/`** (Add new Product)

![add new RPoduct](image-8.png)

### 9. **PUT `/{id}`** (update the product info)

*Note:* Since its a toggle, i choose to first get the previous value of the availability and the do its opposite.

![Update the product](image-9.png)

### 10. **PATCH `/{id}/stock`** (update the stock)

The Body for this route is JSON which is just a number

![update stock](image-10.png)

### 11. **DELETE `/{id}`** (Remove product)

![remove product](image-11.png)


# THE END 