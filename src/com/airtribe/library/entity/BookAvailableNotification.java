package com.airtribe.library.entity;

public class BookAvailableNotification extends Notification {

    public BookAvailableNotification(String message) {
        super(message);
    }

    @Override
    public void send() {
        System.out.println("NOTIFICATION: " + message);
    }
}