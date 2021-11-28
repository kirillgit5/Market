package com.kramar.Market.exception;

public class UserNotExistException extends Exception {
    public UserNotExistException(String message) {
        super(message);
    }
}