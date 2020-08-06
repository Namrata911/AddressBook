package com.book.addressbook.mapper;

import com.book.addressbook.dto.EntryDto;
import com.book.addressbook.entity.AddressBook;
import com.book.addressbook.entity.BookEntry;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EntryMapper {

    EntryMapper INSTANCE = Mappers.getMapper( EntryMapper.class );
    EntryDto entityToDto (BookEntry entry);
    BookEntry dtoToEntity (EntryDto dto);
}
