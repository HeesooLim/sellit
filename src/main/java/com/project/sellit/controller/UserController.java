package com.project.sellit.controller;


import com.project.sellit.model.Role;
import com.project.sellit.model.User;
import com.project.sellit.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

//    @RolesAllowed({Roles.ADMIN})
    @GetMapping("/user")
    public ResponseEntity<List<User>> getUsers() {
        log.info("Found users: "+ userService.getAll());
        return ResponseEntity.ok().body(userService.getAll());
    }

    @PostMapping("/public/user/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/public/user/add").toUriString());
        return ResponseEntity.created(uri).body(userService.add(user));
    }

    @PostMapping("/public/role/add")
    public ResponseEntity<Role> addRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/public/role/add").toUriString());
        return ResponseEntity.created(uri).body(userService.addRole(role));
    }

    @PostMapping("/public/role/assigntouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form) {
        userService.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }
}

@Data
class RoleToUserForm {
    private String username;
    private String roleName;
}
