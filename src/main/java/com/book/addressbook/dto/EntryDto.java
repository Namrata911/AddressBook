package com.book.addressbook.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class EntryDto {

    @NotBlank
    private String firstName;

    @Size(min = 2,max = 15,message = "lastName length should be between 2 to 15 chars")
    private String lastName;

    private String address;
    private String city;
    private String state;
    private String zip;
    private String phoneNo;
}
