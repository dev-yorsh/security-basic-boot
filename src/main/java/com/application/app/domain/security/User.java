package com.application.app.domain.security;

import com.application.app.domain.audit.AuditEntity;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "user", schema = "security_basic")
@Data
public class User extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Integer idUser;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "account_status_code")
    private String accountStatusCode;

    @Column(name = "account_activation_key")
    private String accountActivationKey;

    @Column(name = "link_expiration_time", nullable = false)
    private LocalDateTime linkExpirationTime;

    @Column(name = "photo")
    private String photo;

    @ManyToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "user_role", schema = "security_basic",
            joinColumns = {@JoinColumn(name = "id_user", referencedColumnName = "id_user", foreignKey = @ForeignKey(name = "FK_USER_ROLE_USER"))},
            inverseJoinColumns = {@JoinColumn(name = "id_role", referencedColumnName = "id_role", foreignKey = @ForeignKey(name = "FK_USER_ROLE_ROLE"))})
    private Set<Role> roleList;

}
