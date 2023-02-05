package com.application.core.common;

import com.application.dto.mapper.GenericMapper;
import com.application.security.domain.UserSesion;
import org.springframework.beans.factory.annotation.Autowired;

public class ServiceBase {

    @Autowired
    public UserSesion userSession;

}
