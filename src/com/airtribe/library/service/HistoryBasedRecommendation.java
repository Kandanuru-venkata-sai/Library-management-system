package com.airtribe.library.service;

import com.airtribe.library.entity.Book;
import com.airtribe.library.entity.Patron;

import java.util.ArrayList;
import java.util.List;

public class HistoryBasedRecommendation implements RecommendationStrategy {

    @Override
    public List<Book> recommend(Patron patron, List<Book> books) {

        List<Book> recommendations = new ArrayList<>();

        for (Book borrowedBook : patron.getBorrowingHistory()) {

            for (Book book : books) {

                if (book.getAuthor().equalsIgnoreCase(borrowedBook.getAuthor())
                        && !book.getIsbn().equals(borrowedBook.getIsbn())) {

                    if (!recommendations.contains(book)) {
                        recommendations.add(book);
                    }
                }
            }
        }

        return recommendations;
    }
}