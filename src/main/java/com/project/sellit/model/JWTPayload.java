package com.project.sellit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

@Data
@AllArgsConstructor
public class JWTPayload {
    String username;
    Collection<SimpleGrantedAuthority> authorities;
}
