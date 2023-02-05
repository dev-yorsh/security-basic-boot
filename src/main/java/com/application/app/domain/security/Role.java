package com.application.app.domain.security;

import com.application.app.domain.audit.AuditEntity;
import com.application.core.enums.RoleEnum;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "role", schema = "security_basic")
@Data
public class Role extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private Integer idRole;

    //@Enumerated(EnumType.STRING)
    @Column(name = "name")
    private String name;

    @Column(name = "detail")
    private String detail;
}
