package com.airtribe.library.entity;

public class Book {
    String title;
    boolean isAvailable;
    String isbn;
    String author;
    int publishedYear;
    public Book(String title,String isbn,String author,int publishedYear){
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.publishedYear = publishedYear;
        this.isAvailable = true;
    }
}
