package com.airtribe.library.entity;

import com.airtribe.library.service.Observer;
import com.airtribe.library.service.Subject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Book implements Subject {
    private String title;
    private boolean isAvailable;
    private String isbn;
    private String author;
    private int publishedYear;
    private Set<Observer> observers;
    public Book(String title,String isbn,String author,int publishedYear){
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.publishedYear = publishedYear;
        this.isAvailable = true;
        this.observers = new HashSet<>();
    }
    public void updateBook(String title,String author,int publishedYear){
        this.title = title;
        this.author = author;
        this.publishedYear = publishedYear;
    }
    public String getTitle() {
        return title;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublishedYear() {
        return publishedYear;
    }
    public boolean markBorrowed(){
        if(!this.isAvailable){
            return false;
        }
        else{
            this.isAvailable = false;
            return true;
        }
    }
    public boolean markReturned() {
        if (this.isAvailable) {
            return false;
        }

        this.isAvailable = true;
        notifyObservers(this);
        return true;
    }
    @Override
    public boolean addObserver(Observer observer) {
        return observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Book book) {
        for (Observer observer : observers) {
            observer.update(book);
        }
        observers.clear();
    }
    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", author='" + author + '\'' +
                ", publishedYear=" + publishedYear +
                ", available=" + isAvailable +
                '}';
    }
}
