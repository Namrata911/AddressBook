package com.book.addressbook.repository;

import com.book.addressbook.entity.AddressBook;
import com.book.addressbook.entity.BookEntry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookEntryRepository extends CrudRepository<BookEntry, Integer> {

    public List<BookEntry> findByFirstNameAndLastName(String firstName, String lastName);

}

