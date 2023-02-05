package com.application.app.facade;

import com.application.dto.request.UserFilterRequest;
import com.application.dto.response.BaseResponse;
import com.application.dto.request.UserRequest;
import com.application.dto.response.CollectionResponse;
import com.application.dto.response.UserResponse;

import java.util.Map;

public interface UserFacade {

    BaseResponse<String> saveOrUpdate(UserRequest userRequest);
    CollectionResponse<UserResponse> findAll(UserFilterRequest request);
}
