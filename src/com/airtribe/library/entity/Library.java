package com.airtribe.library.entity;

import java.util.*;

public class Library {
    List<Book> books;
    List<Patron> patrons;
    public Library(){
        this.books = new ArrayList<>();
        this.patrons = new ArrayList<>();
    }
    public boolean addBook(String title,String isbn,String author,int publishedYear){
        Book book = searchBookByISBN(isbn);
        if(book==null){
            books.add(new Book(title,isbn,author,publishedYear));
            return true;
        }else{
            return false;
        }
    }
    public boolean removeBook(String isbn){
        Book book = searchBookByISBN(isbn);
        if(book != null){
            books.remove(book);
            return true;
        }
        return false;
    }
    public boolean updateBook(Book newBook){
        Book book = searchBookByISBN(newBook.isbn);
        if(book!=null){
            book.title = newBook.title;
            book.author = newBook.author;
            book.publishedYear = newBook.publishedYear;
            return true;
        }
        return false;
    }
    public boolean addPatron(String patronName,int id){
        Patron patron = findPatronById(id);
        if(patron == null){
            patrons.add(new Patron(patronName,id));
            return true;
        }
        return false;
    }
    public Patron findPatronById(int id){
        for(Patron p:patrons){
            if(p.patronId == id){
                return p;
            }
        }
        return null;
    }
    public boolean updatePatron(Patron newPatron){
        Patron patron = findPatronById(newPatron.patronId);
        if(patron!=null){
            patron.patronName = newPatron.patronName;
            return true;
        }
        return false;
    }
    public List<Book> searchBooksByAuthor(String author){
        List<Book> booksWithAuthor = new ArrayList<>();
        for(Book book : books){
            if(book.author.equalsIgnoreCase(author)){
                booksWithAuthor.add(book);
            }
        }
        return booksWithAuthor;
    }
    public List<Book> searchBookByTitle(String title){
        List<Book> booksWithTitle = new ArrayList<>();
        for(Book book:books){
            if(book.title.equalsIgnoreCase(title)){
                booksWithTitle.add(book);
            }
        }
        return booksWithTitle;
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Patron> getPatrons() {
        return patrons;
    }

    public Book searchBookByISBN(String isbn){
        for(Book book:books){
            if(book.isbn.equals(isbn)){
                return book;
            }
        }
        return null;
    }
    public boolean lendAbook(Book book,Patron patron){
        Book bookToLend = searchBookByISBN(book.isbn);
        Patron patronToLend = findPatronById(patron.patronId);
        if(bookToLend != null && patronToLend != null){
            if(bookToLend.isAvailable){
                patronToLend.borrowedList.add(bookToLend);
                bookToLend.isAvailable = false;
                return true;
            }
        }
        return false;
    }
    public boolean returnABook(Book book,Patron patron){
        Book bookToReturn = searchBookByISBN(book.isbn);
        Patron patronToReturn = findPatronById(patron.patronId);
        if(bookToReturn != null && patronToReturn != null){
           if(!bookToReturn.isAvailable){
               patronToReturn.borrowedList.remove(bookToReturn);
               bookToReturn.isAvailable = true;
               return true;
           }
        }
        return false;
    }
    public Map<String,List<Book>> statusOfBooks(){
        HashMap<String,List<Book>> statusOfbook = new HashMap<>();
        statusOfbook.put("Available",new ArrayList<>());
        statusOfbook.put("Borrowed Books",new ArrayList<>());
        for(Book book:books){
            if(book.isAvailable){
                statusOfbook.get("Available").add(book);
            }
            else{
                statusOfbook.get("Borrowed Books").add(book);
            }
        }
        return statusOfbook;
    }
}


