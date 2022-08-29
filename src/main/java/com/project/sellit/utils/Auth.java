package com.project.sellit.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.project.sellit.model.JWTPayload;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.Arrays.stream;

public class Auth {
    public static JWTPayload getUserFromToken(String authHeader) {
        String token = authHeader.substring("Bearer ".length());
        Algorithm algo = Algorithm.HMAC256("Secret".getBytes());
        JWTVerifier verifier = JWT.require(algo).build();
        DecodedJWT jwt = verifier.verify(token);

        String username = jwt.getSubject();
        String[] roles = jwt.getClaim("roles").asArray(String.class);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        stream(roles).forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role));
        });

        return new JWTPayload(username, authorities);
    }
}
