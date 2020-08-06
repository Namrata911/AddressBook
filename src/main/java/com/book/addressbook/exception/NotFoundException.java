package com.book.addressbook.exception;

import org.springframework.http.HttpStatus;

/*
To represent when a database resource is not found
 */
public class NotFoundException extends RuntimeException{

    HttpStatus httpStatus;

    public NotFoundException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}

