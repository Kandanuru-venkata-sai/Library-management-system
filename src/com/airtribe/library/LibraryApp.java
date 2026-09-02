package com.airtribe.library;

import com.airtribe.library.entity.Book;
import com.airtribe.library.entity.Library;
import com.airtribe.library.entity.Patron;

public class LibraryApp {
    public static void main(String[] args){
        Library library = new Library();
        library.addBook("Design Patterns", "ISBN-101", "Erich Gamma", 1994);
        library.addBook("Clean Code", "ISBN-102", "Robert Martin", 2008);

        // Add patron
        library.addPatron("Sai", 1);

        // Get actual objects
        Book book = library.searchBookByISBN("ISBN-101");
        Patron patron = library.findPatronById(1);

        // Lend book
        System.out.println("Lend book: " + library.lendAbook(book, patron));

        // Check inventory
        System.out.println("Status after lending:");
        System.out.println(library.statusOfBooks());

        // Try lending the same book again
        System.out.println("Lend same book again: " + library.lendAbook(book, patron));

        // Return book
        System.out.println("Return book: " + library.returnABook(book, patron));

        // Check inventory again
        System.out.println("Status after returning:");
        System.out.println(library.statusOfBooks());
    }
}
