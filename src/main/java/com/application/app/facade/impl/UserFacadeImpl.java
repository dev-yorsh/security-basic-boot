package com.application.app.facade.impl;

import com.application.app.domain.security.User;
import com.application.app.facade.UserFacade;
import com.application.app.service.app.UserService;
import com.application.app.service.notification.NotificationService;
import com.application.core.common.FacadeBase;
import com.application.core.constants.Constants;
import com.application.core.exception.http.BusinessException;
import com.application.core.util.EnvironmentUtil;
import com.application.dto.request.UserFilterRequest;
import com.application.dto.request.UserRequest;
import com.application.dto.response.BaseResponse;
import com.application.dto.response.CollectionResponse;
import com.application.dto.response.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.application.core.constants.Constants.MessageUser.MSJ_SAVE_OK;
import static com.application.core.util.GenericUtil.isEmpty;

@Component
@Slf4j
public class UserFacadeImpl extends FacadeBase implements UserFacade {

    // Filtros
    // Dtos
    // Validaciones de entrada

    @Autowired
    UserService userService;

    @Autowired
    public NotificationService notificationService;

    @Autowired
    EnvironmentUtil environmentUtil;

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<String> saveOrUpdate(UserRequest userRequest) {
        User userEntity = genericMapper.userRequestToUser(userRequest);
        validateFields(userRequest);
        User userRegistered = userService.saveOrUpdate(userEntity);
        String urlConfirmation = generateUrlConfirmation(userRegistered);
        notificationService.sendEmailAccountConfirmation(userRegistered, urlConfirmation);
        return new BaseResponse<>(HttpStatus.CREATED.value(), MSJ_SAVE_OK);
    }

    @Override
    public CollectionResponse<UserResponse> findAll(UserFilterRequest request) {
        log.info("Entering findAll method");
        Page<User> pageList = userService.findAll(request, paginationManager(request));
        return new CollectionResponse<>(genericMapper.listUserToListUserResponse(pageList.getContent()),
                pageList.getNumber(), pageList.getNumberOfElements(), pageList.getTotalPages(),
                pageList.getTotalElements());
    }


    private Pageable paginationManager(UserFilterRequest request) {
        request.setLimit(request.getLimit() != null ? request.getLimit() : Constants.LIMIT_DEFAULT);
        request.setPage(request.getPage() != null ? request.getPage() : Constants.PAGE_DEFAULT);
        return PageRequest.of(request.getPage(), request.getLimit(),
                Sort.by(Sort.Direction.DESC, "idUser"));
    }

    private void validateFields(UserRequest userRequest) {
        if (isEmpty(userRequest.getName()))
            throw new BusinessException("El nombre es requerido");
        if (isEmpty(userRequest.getEmail()))
            throw new BusinessException("El correo es requerido");
        if (isEmpty(userRequest.getUsername()))
            throw new BusinessException("El username no puede ser vacio");
    }

    private String generateUrlConfirmation(User user) {
        return environmentUtil.getServerUrl().concat("/auth/confirm-account?id=")
                .concat(user.getIdUser().toString()).concat("&uuid=").concat(user.getAccountActivationKey());
    }
}
