package com.springboot.project.basicAuth.app.filter;

import com.springboot.project.basicAuth.app.service.BasicAuthService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class BasicAuthFilter extends OncePerRequestFilter {

    @Autowired
    private BasicAuthService basicAuthService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String basicAuthString = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (basicAuthString == null || StringUtils.isBlank(basicAuthString)) {
            filterChain.doFilter(request, response);
            return;
        }
        if (!this.basicAuthService.checkValidBasicAuth(basicAuthString)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.TEXT_PLAIN_VALUE);
            response.getWriter().write("Invalid credentials");
            return;
        }
        filterChain.doFilter(request, response);
    }
}
