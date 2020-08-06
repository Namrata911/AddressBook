package com.book.addressbook.dto;

import lombok.Data;

@Data
public class ErrorResponse {

    public ErrorResponse(){
    }
    public ErrorResponse(String message, Object body){
        this.message=message;
        this.body=body;
    }
    String message;
    Object body;
}
