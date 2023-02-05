package com.application.app.facade;

import com.application.dto.request.ChangePasswordRequest;
import com.application.dto.request.ResetPasswordRequest;
import com.application.dto.response.BaseResponse;

public interface AuthFacade {

    BaseResponse<String> confirmAccount(Integer id, String keyConfirmation);

    BaseResponse<String> resetPassword(ResetPasswordRequest request, Integer id, String keyConfirmation);

    BaseResponse<String> changePassword(ChangePasswordRequest request, Integer id, String keyConfirmation);

    BaseResponse<String> forgotPassword(ResetPasswordRequest request, Integer id, String keyConfirmation);

}
