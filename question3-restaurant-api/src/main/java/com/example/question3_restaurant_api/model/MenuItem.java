package com.example.question3_restaurant_api.model;

public class MenuItem {
    private long id;
    private String name;
    private String description;
    private double price;
    private String category;
    private boolean available;


    public MenuItem(long id, String name, String description, double price, String category, boolean available){
        this.id =id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.available = available;
    }


    public long getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public String getDescription(){
        return this.description;
    }
    public String getCategory(){
        return this.category;
    }
    public double getPrice(){
        return this.price;
    }
    public boolean getAvailability(){
        return this.available;
    }


    public void setId(long id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setCategory(String category){
        this.category = category;
    }
    public void setPrice(double price){
        this.price = price;
    }
    public void setAvailability(boolean available){
        this.available = available;
    }
}
