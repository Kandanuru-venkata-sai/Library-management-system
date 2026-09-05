package com.airtribe.library.service;

import com.airtribe.library.entity.Book;
import com.airtribe.library.entity.Branch;

import java.util.logging.Logger;

public class BranchService {

    private static final Logger logger =
            Logger.getLogger(BranchService.class.getName());

    public boolean transferBook(Book book, Branch fromBranch, Branch toBranch) {

        if (book == null || fromBranch == null || toBranch == null) {
            logger.warning("Book transfer failed: invalid input");
            return false;
        }

        if (!fromBranch.getBooks().contains(book)) {
            logger.warning(
                    "Book transfer failed: book not found in source branch"
            );
            return false;
        }

        if (!book.getIsAvailable()) {
            logger.warning(
                    "Book transfer failed: book is currently borrowed - "
                            + book.getTitle()
            );
            return false;
        }

        fromBranch.removeBook(book);
        toBranch.addBook(book);

        logger.info(
                "Book transferred: " + book.getTitle()
                        + " from branch " + fromBranch.getBranchName()
                        + " to branch " + toBranch.getBranchName()
        );

        return true;
    }
}