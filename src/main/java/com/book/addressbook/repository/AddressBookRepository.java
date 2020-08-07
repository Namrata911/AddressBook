package com.book.addressbook.repository;

import com.book.addressbook.entity.AddressBook;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressBookRepository extends PagingAndSortingRepository<AddressBook, Integer> {

    public Optional<AddressBook> findByName(String name);
}

