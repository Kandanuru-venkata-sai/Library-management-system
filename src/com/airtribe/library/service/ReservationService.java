package com.airtribe.library.service;

import com.airtribe.library.entity.Book;
import com.airtribe.library.entity.Patron;

import java.util.logging.Logger;

public class ReservationService {

    private static final Logger logger =
            Logger.getLogger(ReservationService.class.getName());

    public boolean reserveBook(Book book, Patron patron) {

        if (book == null || patron == null) {
            logger.warning("Invalid reservation request");
            return false;
        }

        if (book.getIsAvailable()) {
            logger.warning(
                    "Cannot reserve available book: " + book.getTitle()
            );
            return false;
        }

        boolean reserved = book.addObserver(patron);

        if (reserved) {
            logger.info(
                    "Book reserved: " + book.getTitle()
                            + " by patron " + patron.getPatronName()
            );
        } else {
            logger.warning(
                    "Book already reserved by patron: "
                            + patron.getPatronName()
            );
        }

        return reserved;
    }
}