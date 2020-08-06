package com.book.addressbook.rest;


import com.book.addressbook.dto.EntryDto;
import com.book.addressbook.dto.Response;
import com.book.addressbook.entity.BookEntry;
import com.book.addressbook.service.BookEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/entry")
public class EntryController {

    @Autowired
    BookEntryService entryService;

    // update an existng entry by id
    @PutMapping("/{entryId}")
    public ResponseEntity<Response> updateEntry(@PathVariable int entryId, @RequestBody @Validated EntryDto dto){
        BookEntry entry = entryService.updateEntryForId(entryId, dto);
        Response response = new Response("OK",entry);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{entryId}")
    public ResponseEntity<Response> deleteEntryById(int entryId){
        entryService.deleteEntry(entryId);
        Response response = new Response("OK",null);
        return ResponseEntity.ok().body(response);
    }
}
