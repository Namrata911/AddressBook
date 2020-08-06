package com.book.addressbook.repository;

import com.book.addressbook.entity.AddressBook;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressBookRepository extends CrudRepository<AddressBook, Integer> {

    public Optional<AddressBook> findByName(String name);
}

