package com.book.addressbook.service;

import com.book.addressbook.dto.EntryDto;
import com.book.addressbook.entity.AddressBook;
import com.book.addressbook.entity.BookEntry;
import com.book.addressbook.exception.NotFoundException;
import com.book.addressbook.mapper.EntryMapper;
import com.book.addressbook.repository.AddressBookRepository;
import lombok.extern.log4j.Log4j2;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class AddressBookService {

    @Autowired
    AddressBookRepository bookRepository;

    public AddressBook createNewBook(String bookName){
        AddressBook book = new AddressBook();
        book.setName(bookName);
        book =  bookRepository.save(book);
        log.debug("created book: {}", book);
        return book;
    }

    public AddressBook addEntryToBook(int bookId, EntryDto dto){
        EntryMapper mapper = Mappers.getMapper(EntryMapper.class);
        BookEntry entry = mapper.dtoToEntity(dto);
        Optional<AddressBook> optBook = bookRepository.findById(bookId);
        if(optBook.isPresent()){
            AddressBook book = optBook.get();
            book.addEntry(entry);
            return bookRepository.save(book);
        }else {
            throw new NotFoundException(String.format("Book Id {} Not Found", bookId));
        }
    }

    public AddressBook getBookById(int bookId){
        Optional<AddressBook> book = bookRepository.findById(bookId);
        if(book.isPresent()){
            return book.get();
        }else{
            throw new NotFoundException(String.format("Book id {} Not Found", bookId));
        }
    }

    public List<AddressBook> getAllBooks() {
        return (List) bookRepository.findAll();
    }

    public void deleteBook(int bookId) {
        bookRepository.deleteById(bookId);
    }
}
