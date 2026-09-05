package com.airtribe.library.entity;

import com.airtribe.library.service.Observer;

import java.util.ArrayList;
import java.util.List;

public class Patron implements Observer {

    private String patronName;
    private int patronId;
    private List<Book> borrowedList;
    private List<Book> borrowingHistory;

    public Patron(String patronName, int id) {
        this.patronName = patronName;
        this.patronId = id;
        this.borrowedList = new ArrayList<>();
        this.borrowingHistory = new ArrayList<>();
    }

    public String getPatronName() {
        return patronName;
    }

    public int getPatronId() {
        return patronId;
    }

    public List<Book> getBorrowedList() {
        return new ArrayList<>(borrowedList);
    }

    public List<Book> getBorrowingHistory() {
        return new ArrayList<>(borrowingHistory);
    }

    public void borrowBook(Book book) {
        this.borrowedList.add(book);
        this.borrowingHistory.add(book);
    }

    public boolean returnBook(Book book) {
        return borrowedList.remove(book);
    }

    public void updatePatron(String name) {
        this.patronName = name;
    }

    @Override
    public void update(Book book) {

        Notification notification =
                new BookAvailableNotification(
                        "Notification for " + patronName +
                                ": Book \"" + book.getTitle() +
                                "\" is now available."
                );

        notification.send();
    }
}