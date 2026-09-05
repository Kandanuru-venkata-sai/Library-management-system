package com.airtribe.library.service;

import com.airtribe.library.entity.Book;
import com.airtribe.library.entity.Patron;

import java.util.List;

public class RecommendationService {

    private RecommendationStrategy strategy;

    public RecommendationService(RecommendationStrategy strategy) {
        this.strategy = strategy;
    }

    public List<Book> getRecommendations(Patron patron, List<Book> books) {
        return strategy.recommend(patron, books);
    }

    public void setStrategy(RecommendationStrategy strategy) {
        this.strategy = strategy;
    }
}