package com.application.app.controller;

import com.application.app.facade.AuthFacade;
import com.application.dto.request.ResetPasswordRequest;
import com.application.dto.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthFacade authFacade;

    @GetMapping("/confirm-account")
    public   BaseResponse<?> confirmAccount(@RequestParam Integer id, @RequestParam String uuid) {
        return authFacade.confirmAccount(id, uuid);
    }

    @PostMapping("/reset-password")
    public BaseResponse<?> con2firmAccount(@Valid  @RequestBody ResetPasswordRequest request,
                                           @RequestParam Integer id, @RequestParam String uuid) {
        return authFacade.resetPassword(request, id, uuid);
    }
}
