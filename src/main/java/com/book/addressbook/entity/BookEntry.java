package com.book.addressbook.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@ToString
public class BookEntry extends AbstractAuditedEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(updatable = false)
    private String firstName;

    @Column(updatable = false)
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String phoneNo;

}
