package com.book.addressbook.rest;


import com.book.addressbook.dto.EntryDto;
import com.book.addressbook.dto.Response;
import com.book.addressbook.entity.AddressBook;
import com.book.addressbook.entity.BookEntry;
import com.book.addressbook.service.AddressBookService;
import com.book.addressbook.service.BookEntryService;
import com.book.addressbook.validation.AllowedValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Validated
public class EntryController {

    @Autowired
    BookEntryService entryService;

    @Autowired
    AddressBookService bookService;

    // Get all entries from a book
    @GetMapping("/book/{bookId}/entry")
    public ResponseEntity<Response> getEntriesForBook(@PathVariable int bookId,
                             @RequestParam(defaultValue = "id") @AllowedValues(values = {"lastname","zip"}) String sortBy){

        List<BookEntry> data = entryService.getEntriesForBook(bookId,sortBy);
        Response response = new Response("OK",data);
        return ResponseEntity.ok().body(response);
    }

    // add entry to book - returns the book object when done
    @PostMapping("/entry")
    public ResponseEntity<Response> addEntryToBook(@RequestParam int bookId, @RequestBody @Valid EntryDto dto){
        AddressBook book = bookService.addEntryToBook(bookId,dto);
        Response response = new Response("OK",book);
        return ResponseEntity.ok().body(response);
    }

    // Delete entry by id
    @DeleteMapping("/entry/{entryId}")
    public ResponseEntity<Response> deleteEntryById(@PathVariable int entryId){
        entryService.deleteEntry(entryId);
        Response response = new Response("OK",null);
        return ResponseEntity.ok().body(response);
    }

    // Partially update entry by id
    @PatchMapping("/entry/{entryId}")
    public ResponseEntity<Response> patchEntryById(@PathVariable int entryId,
                                                   @RequestBody Map<String,Object> updates){
        BookEntry updated = entryService.updateEntryForId(entryId,updates);
        Response response = new Response("OK",updated);
        return ResponseEntity.ok().body(response);
    }
}
