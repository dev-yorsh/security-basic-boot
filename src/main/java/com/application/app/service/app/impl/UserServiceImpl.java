package com.application.app.service.app.impl;

import com.application.app.domain.security.User;
import com.application.app.repository.UserRepository;
import com.application.app.service.app.UserService;
import com.application.core.common.ServiceBase;
import com.application.core.constants.Constants;
import com.application.core.exception.http.BusinessException;
import com.application.core.exception.http.NotFoundException;
import com.application.core.util.GenericUtil;
import com.application.core.util.SecurityUtil;
import com.application.dto.request.UserFilterRequest;
import com.application.dto.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.application.core.constants.Constants.MessageUser.DELETE;
import static com.application.core.constants.Constants.MessageUser.USER_ENABLED;

@Service
@Slf4j
public class UserServiceImpl extends ServiceBase implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Page<User> findAll(UserFilterRequest filter, Pageable pageable) {
        return userRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (!GenericUtil.isEmpty(filter.getIdUser()) && filter.getIdUser() > 0) {
                predicates.add(cb.and(cb.equal(root.get("idUser"), filter.getIdUser())));
            }
            if (!GenericUtil.isEmpty(filter.getUsername())) {
                predicates.add(cb.and(cb.like(cb.lower(
                        root.get("username")), "%".concat(filter.getUsername().toLowerCase()).concat("%"))));
            }
            if (!GenericUtil.isEmpty(filter.getName())) {
                predicates.add(cb.and(cb.like(cb.lower(
                        root.get("name")), "%".concat(filter.getName().toLowerCase()).concat("%"))));
            }
            if (!GenericUtil.isEmpty(filter.getEmail())) {
                predicates.add(cb.and(cb.like(cb.lower(
                        root.get("email")), "%".concat(filter.getEmail().toLowerCase()).concat("%"))));
            }
            if (!GenericUtil.isEmpty(filter.getEnabled())) {
                predicates.add(cb.and(cb.equal(
                        root.get("enabled"), filter.getEnabled())));
            } else {
                predicates.add(cb.and(cb.equal(root.get("enabled"), Boolean.TRUE)));
            }
            if (!GenericUtil.isEmpty(filter.getAccountStatusCode())) {
                predicates.add(cb.and(cb.equal(cb.lower(
                        root.get("accountStatusCode")), filter.getAccountStatusCode().toLowerCase())));
            }
            if (!GenericUtil.isEmpty(filter.getRole())) {
                filter.getRole().forEach(x -> predicates.add(cb.and(cb.like(cb.lower(root.join
                        ("roleList").get("name")), "%".concat(x.toLowerCase().concat("%"))))));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        }, pageable);
    }

    @Override
    public User saveOrUpdate(User user) {
        log.info("[UserServiceImpl | saveUpdate]: start...");
        setUserProperties(user);
        searchRegisteredAccount(user);
        return userRepository.save(user);
    }

    private void setUserProperties(User userEntity) {
        userEntity.setCreatedBy(userSession.userSession().getUsername());
        userEntity.setCreationDate(LocalDateTime.now());
        userEntity.setEnabled(Boolean.TRUE);
        userEntity.setAccountStatusCode(Constants.AccountStatus.PENDING_ACTIVATION);
        userEntity.setAccountActivationKey(SecurityUtil.generateConfirmationKey());
        userEntity.setPassword(passwordEncoder.encode(getTemporaryPassword()));
        userEntity.setLinkExpirationTime(getLinkExpirationTime(userEntity.getCreationDate()));
        if (userEntity.getIdUser() != null) {
            userEntity.setModifiedBy(userSession.userSession().getUsername());
            userEntity.setModificationDate(LocalDateTime.now());
        }
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public BaseResponse<String> findById(Integer id) {
        User userEntity = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
        // return new BaseResponse(HttpStatus.OK.value(), "Correcto",
        //       genericMapper.userToUserResponse(userEntity));
        return null;
    }


    @Override
    public BaseResponse<String> disable(Integer id) {
        User user = findUserById(id);
        user.setEnabled(Boolean.FALSE);
        userRepository.save(user);
        return new BaseResponse<>(HttpStatus.OK.value(), DELETE);
    }

    @Override
    public BaseResponse<String> enable(Integer id) {
        User user = findUserById(id);
        user.setEnabled(Boolean.TRUE);
        userRepository.save(user);
        return new BaseResponse<>(HttpStatus.OK.value(), USER_ENABLED);
    }

    private User findUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
    }

    private void searchRegisteredAccount(User user) {
        if (userRepository.existsByEmail(user.getEmail()))
            throw new BusinessException("El correo ya se encuentra registrado");
        if (userRepository.existsByUsername(user.getUsername()))
            throw new BusinessException("El nombre de usuario ya se encuentra registrado");
    }

    private String getTemporaryPassword() {
        return SecurityUtil.generateRandomKey(14);
    }

    private LocalDateTime getLinkExpirationTime(LocalDateTime creationDate) {
        return creationDate.plusMinutes(Constants.LINK_EXPIRATION_TIME);
    }
    /* private String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    } */

}
