package com.application.dto.mapper;

import com.application.app.domain.security.Role;
import com.application.app.domain.security.User;
import com.application.dto.RoleRequest;
import com.application.dto.RoleResponse;
import com.application.dto.UserRequest;
import com.application.dto.UserResponse;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-20T21:02:02-0500",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.33.0.v20221215-1352, environment: Java 17.0.5 (Eclipse Adoptium)"
)
@Component
public class GenericMapperImpl implements GenericMapper {

    @Override
    public UserResponse userToUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setEmail( user.getEmail() );
        userResponse.setName( user.getName() );
        userResponse.setRoleList( roleSetToRoleResponseSet( user.getRoleList() ) );
        userResponse.setUsername( user.getUsername() );

        return userResponse;
    }

    @Override
    public User userRequestToUser(UserRequest user) {
        if ( user == null ) {
            return null;
        }

        User user1 = new User();

        user1.setEmail( user.getEmail() );
        user1.setName( user.getName() );
        user1.setPassword( user.getPassword() );
        user1.setPhoto( user.getPhoto() );
        user1.setRoleList( roleRequestSetToRoleSet( user.getRoleList() ) );
        user1.setUsername( user.getUsername() );

        return user1;
    }

    @Override
    public UserRequest userToUserRequest(User user) {
        if ( user == null ) {
            return null;
        }

        UserRequest userRequest = new UserRequest();

        userRequest.setEmail( user.getEmail() );
        userRequest.setName( user.getName() );
        userRequest.setPassword( user.getPassword() );
        userRequest.setPhoto( user.getPhoto() );
        userRequest.setRoleList( roleSetToRoleRequestSet( user.getRoleList() ) );
        userRequest.setUsername( user.getUsername() );

        return userRequest;
    }

    @Override
    public List<UserResponse> listUserToListUserResponse(List<User> listUser) {
        if ( listUser == null ) {
            return null;
        }

        List<UserResponse> list = new ArrayList<UserResponse>( listUser.size() );
        for ( User user : listUser ) {
            list.add( userToUserResponse( user ) );
        }

        return list;
    }

    @Override
    public RoleResponse roleToRoleResponse(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleResponse roleResponse = new RoleResponse();

        roleResponse.setDetail( role.getDetail() );
        roleResponse.setName( role.getName() );

        return roleResponse;
    }

    protected Set<RoleResponse> roleSetToRoleResponseSet(Set<Role> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoleResponse> set1 = new LinkedHashSet<RoleResponse>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Role role : set ) {
            set1.add( roleToRoleResponse( role ) );
        }

        return set1;
    }

    protected Role roleRequestToRole(RoleRequest roleRequest) {
        if ( roleRequest == null ) {
            return null;
        }

        Role role = new Role();

        return role;
    }

    protected Set<Role> roleRequestSetToRoleSet(Set<RoleRequest> set) {
        if ( set == null ) {
            return null;
        }

        Set<Role> set1 = new LinkedHashSet<Role>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( RoleRequest roleRequest : set ) {
            set1.add( roleRequestToRole( roleRequest ) );
        }

        return set1;
    }

    protected RoleRequest roleToRoleRequest(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleRequest roleRequest = new RoleRequest();

        return roleRequest;
    }

    protected Set<RoleRequest> roleSetToRoleRequestSet(Set<Role> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoleRequest> set1 = new LinkedHashSet<RoleRequest>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Role role : set ) {
            set1.add( roleToRoleRequest( role ) );
        }

        return set1;
    }
}
