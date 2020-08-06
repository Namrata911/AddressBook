package com.book.addressbook.entity;

import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class AddressBook extends AbstractAuditedEntity{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private List<BookEntry> entryList;

    public AddressBook(){
        this.entryList = new ArrayList<>();
    }

    public void addEntry(BookEntry entry){
        if(this.entryList==null){
            this.entryList = new ArrayList<>();
        }
        this.entryList.add(entry);
    }
    @Override
    public String toString() {
        return "AddressBook{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
