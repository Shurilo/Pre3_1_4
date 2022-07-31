package ru.kata3_1_4.service;

import ru.kata3_1_4.model.User;

import javax.annotation.PostConstruct;
import java.util.List;

public interface UserService {

    List<User> findAll ();
    User getById(long id);
    User save(User user);
    void deleteById(long id);
    User findByUsername(String username);
    User passwordCoder(User user);

    /*@PostConstruct
    void addTestUsers();*/
}
