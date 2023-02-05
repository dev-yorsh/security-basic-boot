package com.application.dto.mapper;

import com.application.app.domain.security.Role;
import com.application.app.domain.security.User;
import com.application.dto.request.RoleRequest;
import com.application.dto.request.UserFilterRequest;
import com.application.dto.request.UserRequest;
import com.application.dto.response.RoleResponse;
import com.application.dto.response.UserResponse;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-04T06:14:18-0500",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.jar, environment: Java 11.0.9 (Oracle Corporation)"
)
@Component
public class GenericMapperImpl implements GenericMapper {

    @Override
    public UserResponse userToUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        if ( user.getIdUser() != null ) {
            userResponse.setIdUser( String.valueOf( user.getIdUser() ) );
        }
        userResponse.setName( user.getName() );
        userResponse.setUsername( user.getUsername() );
        userResponse.setEmail( user.getEmail() );
        userResponse.setRoleList( roleSetToRoleResponseSet( user.getRoleList() ) );

        return userResponse;
    }

    @Override
    public User userRequestToUser(UserRequest user) {
        if ( user == null ) {
            return null;
        }

        User user1 = new User();

        user1.setIdUser( user.getIdUser() );
        user1.setName( user.getName() );
        user1.setUsername( user.getUsername() );
        user1.setEmail( user.getEmail() );
        user1.setPhoto( user.getPhoto() );
        user1.setRoleList( roleRequestSetToRoleSet( user.getRoleList() ) );

        return user1;
    }

    @Override
    public UserRequest userToUserRequest(User user) {
        if ( user == null ) {
            return null;
        }

        UserRequest userRequest = new UserRequest();

        userRequest.setIdUser( user.getIdUser() );
        userRequest.setName( user.getName() );
        userRequest.setEmail( user.getEmail() );
        userRequest.setUsername( user.getUsername() );
        userRequest.setPhoto( user.getPhoto() );
        userRequest.setRoleList( roleSetToRoleRequestSet( user.getRoleList() ) );

        return userRequest;
    }

    @Override
    public User userFilterToUser(UserFilterRequest userFilter) {
        if ( userFilter == null ) {
            return null;
        }

        User user = new User();

        user.setIdUser( userFilter.getIdUser() );
        user.setName( userFilter.getName() );
        user.setUsername( userFilter.getUsername() );
        user.setEmail( userFilter.getEmail() );
        user.setEnabled( userFilter.getEnabled() );
        user.setAccountStatusCode( userFilter.getAccountStatusCode() );

        return user;
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

        roleResponse.setName( role.getName() );
        roleResponse.setDetail( role.getDetail() );

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

        role.setIdRole( roleRequest.getIdRole() );
        role.setName( roleRequest.getName() );
        role.setDetail( roleRequest.getDetail() );

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

        roleRequest.setIdRole( role.getIdRole() );
        roleRequest.setName( role.getName() );
        roleRequest.setDetail( role.getDetail() );

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
