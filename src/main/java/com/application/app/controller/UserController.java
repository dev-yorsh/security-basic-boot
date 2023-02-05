package com.application.app.controller;

import com.application.app.domain.security.User;
import com.application.app.facade.UserFacade;
import com.application.app.service.app.UserService;
import com.application.dto.mapper.GenericMapper;
import com.application.dto.request.UserFilterRequest;
import com.application.dto.request.UserRequest;
import com.application.dto.response.BaseResponse;
import com.application.dto.response.CollectionResponse;
import com.application.dto.response.UserResponse;
import com.application.security.domain.AuthorizedRole;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserFacade userFacade;

    @Autowired
    public GenericMapper genericMapper;

    @PostMapping
    @PreAuthorize(AuthorizedRole.DefineRole.ROLE_ADMINISTRADOR)
    public BaseResponse<?> saveOrUpdate(@Valid @RequestBody UserRequest userRequest) {
        return userFacade.saveOrUpdate(userRequest);
    }

    public ResponseEntity<?> findById(Integer id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize(AuthorizedRole.DefineRole.ROLE_ADMINISTRADOR)
    public CollectionResponse<UserResponse> findAll(@ModelAttribute UserFilterRequest params) {
        return userFacade.findAll(params);
    }

    public ResponseEntity<?> disable(Integer id) {
        return new ResponseEntity<>(userService.disable(id), HttpStatus.OK);
    }

    public ResponseEntity<?> enable(Integer id) {
        return new ResponseEntity<>(userService.enable(id), HttpStatus.OK);
    }

    @GetMapping("asdsad")
    public ResponseEntity<?> find(@AuthenticationPrincipal User user) {
        return null;
        // return new ResponseEntity(userService.fi(), HttpStatus.OK);
    }

    @GetMapping("/me")
    public UserResponse me(@AuthenticationPrincipal User user) throws JsonProcessingException {
        return genericMapper.userToUserResponse(user);
    }
}
