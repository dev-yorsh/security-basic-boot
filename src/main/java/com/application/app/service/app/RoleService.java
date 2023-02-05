package com.application.app.service.app;

import com.application.app.domain.security.Role;

public interface RoleService {

    //    void addRoleToUser(String role, String username);
    Role find(String name);

    Role save(Role role);
}
