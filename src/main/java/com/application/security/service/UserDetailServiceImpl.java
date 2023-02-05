package com.application.security.service;

import com.application.app.domain.security.Role;
import com.application.app.domain.security.User;
import com.application.app.service.app.UserService;
import com.application.core.exception.http.InternalException;
import com.application.security.domain.UserDetail;
import com.application.security.domain.UserSesion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.application.core.constants.Constants.AccountStatus.*;

@Service
@Slf4j
@Transactional
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserSesion userSession;

    @Override
    public UserDetails loadUserByUsername(String username) {
        log.info("[UserDetailServiceImpl | loadUserByUsername]: start...");
        User user = userService.findByUsername(username);
        checkAccountStatus(user);
        List<GrantedAuthority> grantedAuthorities = getAuthorities(user.getRoleList());
        return new UserDetail(user.getIdUser(), user.getName(), user.getUsername(), user.getPassword(), grantedAuthorities);
    }

    private void checkAccountStatus(User user) {
        if (user == null)
            throw new InternalException("Su cuenta no se encuentra registrada.");
        if (user.getEnabled() == null || !user.getEnabled())
            throw new InternalException("Su cuenta fue eliminada del sistema.");
        if (user.getAccountStatusCode().equals(PENDING_ACTIVATION))
            throw new InternalException("Su cuenta no esta activada, por favor revise su correo.");
        if (user.getAccountStatusCode().equals(SUSPENDED))
            throw new InternalException("Su cuenta fue suspendida por el administrador del sistema.");
    }

    private List<GrantedAuthority> getAuthorities(Set<Role> role) {
        return role.stream().map(x -> new SimpleGrantedAuthority(x.getName()))
                .collect(Collectors.toList());
    }

}
