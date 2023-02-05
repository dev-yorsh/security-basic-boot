package com.application;

import com.application.app.domain.security.User;
import com.application.app.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class SecuritytApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Test
    void crearUserTest() {
        /*User user = new User();
        user.setName("Usuario Administrador");
        user.setUsername("admin");
        user.setPassword(encoder.encode("admin-2023"));
        user.setEmail("system.developer07@gmail.com");
        user.setIsAccountVerified(true);
        user.setEnabled(true);
        User s = userRepository.save(user);
        assert(s.getUsername().equals(user.getUsername()));*/
    }

}
