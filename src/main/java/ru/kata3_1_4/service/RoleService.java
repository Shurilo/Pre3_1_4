package ru.kata3_1_4.service;

import ru.kata3_1_4.model.Role;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;

public interface RoleService {
    List<Role> findAllRole();

    /*@PostConstruct
    void addDefaultRole();
*/
    Set<Role> findByIdRoles(List<Long>roles);
}
