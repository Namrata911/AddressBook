package com.book.addressbook.service;

import com.book.addressbook.dto.EntryDto;
import com.book.addressbook.entity.BookEntry;
import com.book.addressbook.exception.NotFoundException;
import com.book.addressbook.mapper.EntryMapper;
import com.book.addressbook.repository.BookEntryRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookEntryService {

    @Autowired
    BookEntryRepository entryRepository;

    public BookEntry updateEntryForId(int id, EntryDto dto){
        EntryMapper mapper = Mappers.getMapper(EntryMapper.class);
        BookEntry entry = mapper.dtoToEntity(dto);

        Optional<BookEntry> oldEntry = entryRepository.findById(id);
        if(oldEntry.isPresent()){
            entry.setId(oldEntry.get().getId());
            return entryRepository.save(entry);
        }else{
            throw new NotFoundException(String.format("Entry Id {} was not found",id));
        }
    }

    public void deleteEntry(int entryId) {
        entryRepository.deleteById(entryId);
    }
}
