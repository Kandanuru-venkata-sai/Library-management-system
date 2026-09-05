package com.airtribe.library.service;

import com.airtribe.library.entity.Book;
import com.airtribe.library.entity.Patron;

import java.util.ArrayList;
import java.util.List;

public class AvailableBooksRecommendation implements RecommendationStrategy {

    @Override
    public List<Book> recommend(Patron patron, List<Book> books) {

        List<Book> recommendations = new ArrayList<>();

        for (Book book : books) {
            if (book.getIsAvailable()
                    && !patron.getBorrowedList().contains(book)) {
                recommendations.add(book);
            }
        }

        return recommendations;
    }
}