package com.application.app.service.app.impl;

import com.application.app.domain.security.Role;
import com.application.app.repository.RoleRepository;
import com.application.app.service.app.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role find(String name) {
        return roleRepository.findByName(name).orElse(null);
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }
}
