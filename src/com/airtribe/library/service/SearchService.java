package com.airtribe.library.service;

import com.airtribe.library.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class SearchService {
    public List<Book> searchBooksByAuthor(String author,List<Book> books){
        List<Book> booksWithAuthor = new ArrayList<>();
        for(Book book : books){
            if(book.getAuthor().equalsIgnoreCase(author)){
                booksWithAuthor.add(book);
            }
        }
        return booksWithAuthor;
    }
    public List<Book> searchBookByTitle(String title,List<Book> books){
        List<Book> booksWithTitle = new ArrayList<>();
        for(Book book:books){
            if(book.getTitle().equalsIgnoreCase(title)){
                booksWithTitle.add(book);
            }
        }
        return booksWithTitle;
    }

    public Book searchBookByISBN(String isbn,List<Book> books){
        for(Book book:books){
            if(book.getIsbn().equals(isbn)){
                return book;
            }
        }
        return null;
    }
}
