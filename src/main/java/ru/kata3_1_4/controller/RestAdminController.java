package ru.kata3_1_4.controller;

import ru.kata3_1_4.exception.ExceptionInfo;
import ru.kata3_1_4.exception.UserUsernameExistException;
import ru.kata3_1_4.model.User;
import ru.kata3_1_4.service.RoleService;
import ru.kata3_1_4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RestAdminController {

    private final UserService userService;

    @Autowired
    public RestAdminController(RoleService roleService, UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @PostMapping("/users")
    public ResponseEntity<ExceptionInfo> createUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String error = getErrorsFromBindingResult(bindingResult);
            return new ResponseEntity<>(new ExceptionInfo(error), HttpStatus.BAD_REQUEST);
        }
        try {
            userService.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (UserUsernameExistException u) {
            throw new UserUsernameExistException("User with username exist");
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<ExceptionInfo> deleteUser (@PathVariable("id") long id) {
        userService.deleteById(id);
        return new ResponseEntity<>(new ExceptionInfo("User deleted"), HttpStatus.OK);
    }

    @GetMapping("users/{id}")
    public ResponseEntity<User> getUserById (@PathVariable("id") long id) {
        User user = userService.getById(id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }



    @PutMapping("/users/{id}")
    public ResponseEntity<ExceptionInfo> updateUser(@PathVariable("id") long id,
                         @Valid @RequestBody User user,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String error = getErrorsFromBindingResult(bindingResult);
            return new ResponseEntity<>(new ExceptionInfo(error), HttpStatus.BAD_REQUEST);
        }

        userService.getById(id).getPassword();
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    private String getErrorsFromBindingResult(BindingResult bindingResult) {
        return bindingResult.getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("; "));
    }

}