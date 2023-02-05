package com.application.security.domain;

import com.application.core.constants.SecurityConstants;

public class AuthorizedRole {
    public static class DefineRole {
        public static final String ROLE_ADMINISTRADOR = "hasAuthority(\"" + SecurityConstants.ROLE_ADMINISTRADOR + "\")";
        public static final String ROLE_PUBLICO = "hasAuthority(\"" + SecurityConstants.ROLE_PUBLICO + "\")";
        public static final String ROLE_ADMINISTRADOR_AND_PUBLICO= "hasAuthority(\"" + SecurityConstants.ROLE_PUBLICO + "\") or" +
                " hasAuthority(\"" + SecurityConstants.ROLE_PUBLICO + "\")";
    }
}
