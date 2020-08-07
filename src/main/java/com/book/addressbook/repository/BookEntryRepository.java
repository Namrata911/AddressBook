package com.book.addressbook.repository;

import com.book.addressbook.entity.BookEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookEntryRepository extends JpaRepository<BookEntry, Integer> {

    public List<BookEntry> findByFirstNameAndLastName(String firstName, String lastName);

}

