package com.application.app.domain.audit;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public class AuditEntity {

    @Column(name = "created_by", updatable = false)
    public String createdBy;

    @Column(name = "creation_date" , updatable = false)
    public LocalDateTime creationDate;

    @Column(name = "modified_by")
    public String modifiedBy;

    @Column(name = "modification_date")
    public LocalDateTime ModificationDate;
}
