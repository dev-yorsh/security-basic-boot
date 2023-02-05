package com.application.app.service.app.impl;

import com.application.app.domain.security.User;
import com.application.app.repository.UserRepository;
import com.application.app.service.app.AuthService;
import com.application.app.service.notification.NotificationService;
import com.application.core.exception.http.InternalException;
import com.application.core.exception.http.NotFoundException;
import com.application.core.util.SecurityUtil;
import com.application.dto.request.ResetPasswordRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.application.core.constants.Constants.AccountStatus.ACTIVATED;
import static com.application.core.constants.Constants.AccountStatus.SUSPENDED;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User confirmAccount(Integer id, String keyConfirmation) {
        log.info("[AuthServiceImpl | confirmAccount]: start...");
        checkAccountStatus(id, keyConfirmation);
        checkAccountIsConfirmed(id);
        User user = findUserById(id);
        user.setAccountStatusCode(ACTIVATED);
        return userRepository.save(user);
    }

    @Override
    public User resetPassword(ResetPasswordRequest passwordRequest, Integer id, String keyConfirmation) {
        checkAccountStatus(id, keyConfirmation);
        User user = findUserById(id);
        checkLinkExpiration(user.getLinkExpirationTime());
        user.setPassword(passwordEncoder.encode(passwordRequest.getNewPassword()));
        return userRepository.save(user);
    }

    @Override
    public void changePassword(String password) {

    }

    @Override
    public void forgotPassword(String password) {

    }

    private void checkAccountStatus(Integer id, String keyConfirmation) {
        if (!userRepository.existsByIdUserAndEnabledIsTrue(id))
            throw new InternalException("El usuario no se encuentra registrado o no esta habilitado");
        if (!userRepository.existsByIdUserAndAccountActivationKey(id, keyConfirmation))
            throw new InternalException("Código de activación inválido");
        if (userRepository.existsByIdUserAndAccountStatusCodeContaining(id, SUSPENDED))
            throw new InternalException("La cuenta fue suspendida por el administrador del sistema.");
    }

    public void checkAccountIsConfirmed(Integer id) {
        if (userRepository.existsByIdUserAndAccountStatusCodeContaining(id, ACTIVATED))
            throw new InternalException("La cuenta ya ha sido activada previamente");
    }

    private User findUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
    }

    private void checkLinkExpiration(LocalDateTime expirationTime){
        if(!LocalDateTime.now().isBefore(expirationTime)){
           throw new InternalException("El enlace ha caducado. Por favor contactese con el administrador del sistema.");
       }
    }
}
