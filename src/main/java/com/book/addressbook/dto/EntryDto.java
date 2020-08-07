package com.book.addressbook.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class EntryDto {

    @NotBlank(message = "firstName must not be blank")
    private String firstName;

    @Size(min = 3,max = 15,message = "lastName length should be between 2 to 15 chars")
    private String lastName;

    @NotBlank
    private String address;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    @Pattern(regexp = "[1-9][0-9]{5}", message = "zip should be 6 digits long")
    private String zip;

    @Pattern(regexp = "[1-9][0-9]{9}", message = "Phone number should be 10 digit long")
    private String phoneNo;
}
