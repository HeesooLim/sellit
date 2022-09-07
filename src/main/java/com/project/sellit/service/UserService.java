package com.project.sellit.service;

import com.project.sellit.model.Role;
import com.project.sellit.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAll();
    User add(User user);
    Role addRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    void updateUser(String username, String email, String firstName, String lastName, String password);
}
