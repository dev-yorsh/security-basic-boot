package com.application.app.service.app;

import com.application.app.domain.security.User;
import com.application.dto.request.UserFilterRequest;
import com.application.dto.response.BaseResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    Page<User> findAll(UserFilterRequest userFilter, Pageable pageable);

    User saveOrUpdate(User user);

    User findByUsername(String username);

    BaseResponse<String> findById(Integer id);

    BaseResponse<String> disable(Integer id);

    BaseResponse<String> enable(Integer id);
}
