package com.project.sellit.filter;

import com.project.sellit.model.JWTPayload;
import com.project.sellit.utils.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@Slf4j
public class AuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().equals("/api/login")) {
            filterChain.doFilter(request, response);
        }
        else {
            String authorizationHeader = request.getHeader(AUTHORIZATION);
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                try {
                    JWTPayload payload = Auth.getUserFromToken(authorizationHeader);
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(payload.getUsername(), null, payload.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    filterChain.doFilter(request, response);
                }
                catch(Exception e) {
                    log.info(e.getMessage());
                    response.setHeader("error", e.getMessage());
                    response.sendError(FORBIDDEN.value());
                }
            }
            else {
                filterChain.doFilter(request, response);
            }
        }
    }
}
