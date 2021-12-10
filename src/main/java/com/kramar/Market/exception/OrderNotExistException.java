package com.kramar.Market.exception;

public class OrderNotExistException extends RuntimeException {
    public OrderNotExistException(String message) {
        super(message);
    }
}
