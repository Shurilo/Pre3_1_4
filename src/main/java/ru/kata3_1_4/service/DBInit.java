package ru.kata3_1_4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata3_1_4.model.Role;
import ru.kata3_1_4.model.User;
import ru.kata3_1_4.repository.RoleRepository;
import ru.kata3_1_4.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Component
public class DBInit {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User passwordCoder(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return user;
    }
    public void addDefaultRole() {
        roleRepository.save(new Role("ROLE_USER"));
        roleRepository.save(new Role("ROLE_ADMIN"));
    }



    private void addDefaultUsers() {
        Set<Role> userSet = new HashSet<>();
        userSet.add(roleRepository.findById(1L).orElse(null));
        Set<Role> adminSet = new HashSet<>();
        adminSet.add(roleRepository.findById(1L).orElse(null));
        adminSet.add(roleRepository.findById(2L).orElse(null));
        User admin = new User("Shur", "Push", (byte)41, "alex@mail.com", "admin",
                "12345",adminSet);
        User user = new User("Sveta", "Ryzh", (byte)48, "sveta@mail.com", "user",
                "00000", userSet);
        userRepository.save(passwordCoder(admin));
        userRepository.save(passwordCoder(user));
    }

}
