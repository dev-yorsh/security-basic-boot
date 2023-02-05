package com.application.app.repository;

import com.application.app.domain.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    User findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByIdUserAndEnabledIsTrue(Integer idUser);

    Boolean existsByIdUserAndAccountActivationKey(Integer id, String keyConfirmation);

    Boolean existsByIdUserAndAccountStatusCodeContaining(Integer id, String keyConfirmation);

}
