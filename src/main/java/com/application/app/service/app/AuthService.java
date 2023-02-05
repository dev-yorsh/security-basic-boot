package com.application.app.service.app;

import com.application.app.domain.security.User;
import com.application.dto.request.ChangePasswordRequest;
import com.application.dto.request.ResetPasswordRequest;

public interface AuthService {

    User confirmAccount(Integer id, String keyConfirmation);
    User resetPassword(ResetPasswordRequest passwordRequest, Integer id, String keyConfirmation);
    void changePassword(String password);
    void forgotPassword(String password);

}
