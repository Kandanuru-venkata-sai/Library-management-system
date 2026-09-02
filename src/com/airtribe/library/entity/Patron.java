package com.airtribe.library.entity;

import java.util.ArrayList;
import java.util.List;

public class Patron {
    String patronName;
    int patronId;
    List<Book> borrowedList;
    public Patron(String patronName,int id) {
        this.patronName = patronName;
        this.patronId = id;
        this.borrowedList = new ArrayList<Book>();
    }
}
