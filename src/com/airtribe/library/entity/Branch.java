package com.airtribe.library.entity;

import java.util.ArrayList;
import java.util.List;

public class Branch {

    private int branchId;
    private String branchName;
    private List<Book> books;

    public Branch(int branchId, String branchName) {
        this.branchId = branchId;
        this.branchName = branchName;
        this.books = new ArrayList<>();
    }

    public int getBranchId() {
        return branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public List<Book> getBooks() {
        return new ArrayList<>(books);
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public boolean removeBook(Book book) {
        return books.remove(book);
    }
}