package com.revature.exceptions;

public class PasswordsDontMatchException extends Exception{

    public PasswordsDontMatchException(String message) {
        super(message);
    }
}
