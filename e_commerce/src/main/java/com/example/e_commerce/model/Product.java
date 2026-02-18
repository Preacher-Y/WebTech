package com.example.e_commerce.model;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;

    @Column(name = "category")
    private String category;

    @Column(name = "stock_quantity")
    private int stockQuantity;

    @Column(name = "brand")
    private String brand;

    protected Product() {
    }

    public Product(Long productId, String name, String description, double price, String category, int stockQuantity, String brand){
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.stockQuantity = stockQuantity;
        this.brand = brand;
    }

    public long getId(){
        return this.productId;
    }
    public String getName(){
        return this.name;
    }
    public String getDescription(){
        return this.description;
    }
    public double getPrice(){
        return this.price;
    }
    public String getCategory(){
        return this.category;
    }
    public int getStockQuantity(){
        return this.stockQuantity;
    }
    public String getBrand(){
        return this.brand;
    }

    public void setId(long id){
        this.productId = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setDescription(String desc){
        this.description = desc;
    }
    public void setPrice(double price){
        this.price = price;
    }
    public void setCategory(String cat){
        this.category = cat;
    }
    public void setStockQuantity(int qty){
        this.stockQuantity = qty;
    }
    public void setBrand(String brand){
        this.brand = brand;
    }
}
