package com.application.dto.mapper;

import com.application.app.domain.security.Role;
import com.application.app.domain.security.User;
import com.application.dto.request.UserFilterRequest;
import com.application.dto.response.RoleResponse;
import com.application.dto.request.UserRequest;
import com.application.dto.response.UserResponse;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenericMapper {

    /********************** User Mapper **********************/
    @Named(value = "userResponse")
    UserResponse userToUserResponse(User user);

    User userRequestToUser(UserRequest user);

    UserRequest userToUserRequest(User user);

    User userFilterToUser(UserFilterRequest userFilter);


    @IterableMapping(qualifiedByName = "userResponse")
    List<UserResponse> listUserToListUserResponse(List<User> listUser);

    /********************** Rol Mapper **********************/
    RoleResponse roleToRoleResponse(Role role);

}
