package com.project.sellit.controller;


import com.project.sellit.model.JWTPayload;
import com.project.sellit.model.Role;
import com.project.sellit.model.User;
import com.project.sellit.service.UserService;
import com.project.sellit.utils.Auth;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @GetMapping("/admin/user")
    public ResponseEntity<List<User>> getUsers() {
        log.info("Found users: "+ userService.getAll());
        return ResponseEntity.ok().body(userService.getAll());
    }

    @PostMapping("/user/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/public/user/add").toUriString());
        return ResponseEntity.created(uri).body(userService.add(user));
    }

    @PostMapping("/role/add")
    public ResponseEntity<Role> addRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/public/role/add").toUriString());
        return ResponseEntity.created(uri).body(userService.addRole(role));
    }

    @PostMapping("/role/assigntouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form) {
        userService.addRoleToUser(form.getUsername(), form.getRolename());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUser(@RequestHeader (name="Authorization") String token) {
        JWTPayload payload = Auth.getUserFromToken(token);

        return ResponseEntity.ok().body(userService.getUser(payload.getUsername()));
    }
}

@Data
class RoleToUserForm {
    private String username;
    private String rolename;
}
