package com.book.addressbook.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditedEntity implements Serializable {

    @Column(nullable = false, updatable = false)
    @CreatedDate
    @JsonIgnore
    private Instant createdDate;

    @Column(nullable = false)
    @LastModifiedDate
    @JsonIgnore
    private Instant modifiedDate;

    @CreatedBy
    @JsonIgnore
    private String createdBy;

}
