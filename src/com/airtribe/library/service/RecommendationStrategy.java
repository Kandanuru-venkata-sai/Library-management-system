package com.airtribe.library.service;

import com.airtribe.library.entity.Book;
import com.airtribe.library.entity.Patron;

import java.util.List;

public interface RecommendationStrategy {

    List<Book> recommend(Patron patron, List<Book> books);
}