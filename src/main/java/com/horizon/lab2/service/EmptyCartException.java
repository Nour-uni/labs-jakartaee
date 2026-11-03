package com.horizon.lab2.service;

import jakarta.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class EmptyCartException extends RuntimeException {
    public EmptyCartException(String message) {
        super(message);
    }
}
