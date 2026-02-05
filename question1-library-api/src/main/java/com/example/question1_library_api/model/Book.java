package com.example.question1_library_api.model;

public class Book {
    private long id;
    private String title;
    private String author;
    private String ISNB;
    private int publicationYear;

    public Book(long id, String title, String author, String ISNB, int publicationYear){
        this.id = id;
        this.title = title;
        this.author=author;
        this.ISNB = ISNB;
        this.publicationYear= publicationYear;
    }

    public long getId(){
        return this.id;
    }
    
    public String getTitle(){
        return this.title;
    }

    public String getAuthor(){
        return this.author;
    }

    public String getISNB(){
        return this.ISNB;
    }

    public int getPublicationYear(){
        return this.publicationYear;
    }

    public void setId(long id){
        this.id=id;
    }
    
    public void setTitle(String title){
        this.title = title;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public void setISNB(String ISNB){
        this.ISNB = ISNB;
    }

    public void setPublicationYear(int publicationYear){
        this.publicationYear = publicationYear;
    }
}
