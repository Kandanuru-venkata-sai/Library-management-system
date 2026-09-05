package com.airtribe.library.service;

import com.airtribe.library.entity.Book;
import com.airtribe.library.entity.Patron;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class LendingService {
    private static final Logger logger =
            Logger.getLogger(LendingService.class.getName());
    public boolean lendABook(
            Book book,
            Patron patron,
            List<Book> books,
            List<Patron> patrons) {

        Book bookToLend = findBookByISBN(book.getIsbn(), books);
        Patron patronToLend = findPatronById(patron.getPatronId(), patrons);

        if (bookToLend != null && patronToLend != null) {

            if (bookToLend.getIsAvailable()) {
                patronToLend.borrowBook(bookToLend);
                bookToLend.markBorrowed();

                logger.info(
                        "Book borrowed: " + bookToLend.getTitle()
                                + " by patron " + patronToLend.getPatronName()
                );

                return true;
            }

            logger.warning(
                    "Book already borrowed: " + bookToLend.getTitle()
            );
        }

        return false;
    }

    public boolean returnABook(
            Book book,
            Patron patron,
            List<Book> books,
            List<Patron> patrons) {

        Book bookToReturn = findBookByISBN(book.getIsbn(), books);
        Patron patronToReturn = findPatronById(patron.getPatronId(), patrons);

        if (bookToReturn != null && patronToReturn != null) {
            if (!bookToReturn.getIsAvailable()) {

                boolean returnedByPatron =
                        patronToReturn.returnBook(bookToReturn);

                if (!returnedByPatron) {
                    logger.warning(
                            "Return failed: patron did not borrow the book"
                    );
                    return false;
                }

                bookToReturn.markReturned();

                logger.info(
                        "Book returned: " + bookToReturn.getTitle()
                                + " by patron " + patronToReturn.getPatronName()
                );

                return true;
            }

            logger.warning(
                    "Book is already available: " + bookToReturn.getTitle()
            );
        }

        return false;
    }

    public Map<String, List<Book>> statusOfBooks(List<Book> books) {
        HashMap<String, List<Book>> statusOfBook = new HashMap<>();

        statusOfBook.put("Available", new ArrayList<>());
        statusOfBook.put("Borrowed Books", new ArrayList<>());

        for (Book book : books) {
            if (book.getIsAvailable()) {
                statusOfBook.get("Available").add(book);
            } else {
                statusOfBook.get("Borrowed Books").add(book);
            }
        }

        return statusOfBook;
    }

    private Book findBookByISBN(String isbn, List<Book> books) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }

        return null;
    }

    private Patron findPatronById(int id, List<Patron> patrons) {
        for (Patron patron : patrons) {
            if (patron.getPatronId() == id) {
                return patron;
            }
        }

        return null;
    }
}