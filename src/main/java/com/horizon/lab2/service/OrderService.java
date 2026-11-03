package com.horizon.lab2.service;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@Stateless
public class OrderService {

    @Inject
    private NotificationService notificationService;

    @Inject
    private CartService cartService;

    @Transactional
    public void createOrder(String name, String email, String address) {
        if (cartService.getItems().isEmpty()) {
            throw new EmptyCartException("Cannot check out with an empty cart");
        }
        // Order persistence arrives in Chapter 4
        notificationService.logOrderConfirmation(name);
        cartService.clear();
    }
}