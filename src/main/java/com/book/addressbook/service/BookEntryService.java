package com.book.addressbook.service;

import com.book.addressbook.dto.EntryDto;
import com.book.addressbook.entity.AddressBook;
import com.book.addressbook.entity.BookEntry;
import com.book.addressbook.exception.GeneralException;
import com.book.addressbook.exception.NotFoundException;
import com.book.addressbook.mapper.EntryMapper;
import com.book.addressbook.repository.AddressBookRepository;
import com.book.addressbook.repository.BookEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.server.ServerErrorException;

import java.awt.print.Book;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

@Service
@Slf4j
public class BookEntryService {

    @Autowired
    BookEntryRepository entryRepository;

    @Autowired
    AddressBookRepository bookRepository;

    public BookEntry updateEntryForId(int id, Map<String,Object> updates){
//        EntryMapper mapper = Mappers.getMapper(EntryMapper.class);
//        BookEntry entry = mapper.dtoToEntity(dto);

        Optional<BookEntry> entryOptional = entryRepository.findById(id);

        if(entryOptional.isPresent()){
            BookEntry entry = entryOptional.get();
            if(updates.containsKey("FirstName")|| updates.containsKey("LastName")){
                throw new GeneralException("Cannot update FirstName and LastName");
            }
            updates.forEach((k,v)->{
                Field field = ReflectionUtils.findField(BookEntry.class, k);
                try {
                    Method method =BookEntry.class.getMethod("set"+k , new Class[] {String.class});
                    method.invoke(entry, v);
                } catch (NoSuchMethodException e) {
                    log.error("Invalid field name in updated", e);
                } catch (IllegalAccessException e) {
                    log.error("IllegalAccess: ",e);
                } catch (InvocationTargetException e) {
                    log.error("Invocation Exception: ", e);
                }

            });
            entryRepository.saveAndFlush(entry);
            return entryRepository.findById(id).get();
        }else{
            throw new NotFoundException(String.format("Entry Id {} was not found",id));
        }
    }

    public void deleteEntry(int entryId) {
        entryRepository.deleteById(entryId);
    }

    public List<BookEntry> getEntriesForBook(int bookId, String sortBy) {
        Optional<AddressBook> book = bookRepository.findById(bookId);
        if(book.isPresent()){
            List<BookEntry> entryList = book.get().getEntryList();
            if(sortBy.equals("lastname")) {
                Collections.sort(entryList, Comparator.comparing(BookEntry::getLastName).thenComparing(BookEntry::getFirstName));
            }
            else if(sortBy.equals("zip")){
                Collections.sort(entryList, Comparator.comparing(BookEntry::getZip).thenComparing(BookEntry::getFirstName));
            }
            return entryList;
        }else{
            throw new NotFoundException(String.format("Book Id {} was not found", bookId));
        }
    }
}
