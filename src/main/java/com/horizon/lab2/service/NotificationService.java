package com.horizon.lab2.service;

import jakarta.ejb.Lock;
import jakarta.ejb.LockType;
import jakarta.ejb.Singleton;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Singleton
public class NotificationService {

    public record Confirmation(String customerName, LocalDateTime timestamp) {}

    private static final int MAX_HISTORY = 5;
    private final LinkedList<Confirmation> history = new LinkedList<>();

    @Lock(LockType.WRITE)
    public void logOrderConfirmation(String customerName) {
        System.out.println("[NOTIFY] Order confirmed for: " + customerName);
        history.addFirst(new Confirmation(customerName, LocalDateTime.now()));
        if (history.size() > MAX_HISTORY) {
            history.removeLast();
        }
    }

    @Lock(LockType.READ)
    public List<Confirmation> getHistory() {
        return new ArrayList<>(history);
    }
}