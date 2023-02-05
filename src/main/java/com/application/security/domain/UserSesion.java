package com.application.security.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@NoArgsConstructor
public class UserSesion {

    private Integer idUser;
    private String name;
    private String username;
    private String ip;
    private String host;

    public UserSesion(Integer idUser, String name, String username) {
        this.idUser = idUser;
        this.name = name;
        this.username = username;
    }

    public UserSesion userSession(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetail userSession = (UserDetail) authentication.getPrincipal();
        return new UserSesion(userSession.getIdUser(), userSession.getName(), userSession.getUsername());
    }

}
