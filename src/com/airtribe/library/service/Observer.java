package com.airtribe.library.service;

import com.airtribe.library.entity.Book;

public interface Observer {
    void update(Book book);
}