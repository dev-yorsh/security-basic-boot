package com.application.app.facade.impl;

import com.application.app.domain.security.User;
import com.application.app.facade.AuthFacade;
import com.application.app.service.app.AuthService;
import com.application.app.service.notification.NotificationService;
import com.application.core.common.FacadeBase;
import com.application.core.exception.http.BusinessException;
import com.application.core.util.EnvironmentUtil;
import com.application.core.util.GenericUtil;
import com.application.dto.request.ChangePasswordRequest;
import com.application.dto.request.ResetPasswordRequest;
import com.application.dto.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.application.core.constants.Constants.MessageUser.MSJ_CONFIRMATION_OK;
import static com.application.core.constants.Constants.MessageUser.MSJ_RESET_PASSWORD_OK;

@Service
public class AuthFacadeImpl extends FacadeBase implements AuthFacade {

    @Autowired
    private AuthService authService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    public BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private EnvironmentUtil environmentUtil;

    @Override
    public BaseResponse<String> confirmAccount(Integer id, String keyConfirmation) {
        User confirmedUser = authService.confirmAccount(id, keyConfirmation);
        notificationService.sendEmailResetPassword(confirmedUser, generateUrlResetPassword(confirmedUser));
        return new BaseResponse<>(HttpStatus.OK.value(), MSJ_CONFIRMATION_OK);
    }

    @Override
    public BaseResponse<String> resetPassword(ResetPasswordRequest request, Integer id, String keyConfirmation) {
        validatePassword(request);
        User userChanged = authService.resetPassword(request, id, keyConfirmation);
        notificationService.sendAlertEmailChangePassword(userChanged);
        return new BaseResponse<>(HttpStatus.OK.value(), MSJ_RESET_PASSWORD_OK);
    }

    @Override
    public BaseResponse<String> changePassword(ChangePasswordRequest request, Integer id, String keyConfirmation) {
        return null;
    }

    @Override
    public BaseResponse<String> forgotPassword(ResetPasswordRequest request, Integer id, String keyConfirmation) {
        return null;
    }

    private void validatePassword(ResetPasswordRequest request) {
        if (GenericUtil.isEmpty(request.getNewPassword()))
            throw new BusinessException("La contraseña no puede ser vacia");
        if (!request.getNewPassword().equals(request.getPasswordRepeat()))
            throw new BusinessException("Las contraseñas deben coincidir");
    }

    private String generateUrlResetPassword(User user) {
        return environmentUtil.getServerUrl().concat("/auth/reset-password?id=")
                .concat(user.getIdUser().toString()).concat("&uuid=").concat(user.getAccountActivationKey());
    }
}
