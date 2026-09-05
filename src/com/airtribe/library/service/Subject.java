package com.airtribe.library.service;

import com.airtribe.library.entity.Book;

public interface Subject {

    boolean addObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers(Book book);
}