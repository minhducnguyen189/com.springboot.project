package com.springboot.project.basicAuth.app.filter;

import com.springboot.project.basicAuth.app.service.BasicAuthService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
public class BasicAuthFilter implements Filter {

    @Autowired
    private BasicAuthService basicAuthService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String basicAuthString = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if (basicAuthString != null && StringUtils.isNoneBlank(basicAuthString)) {
            String username = basicAuthService.getUsername(basicAuthString);
            String password = basicAuthService.getPassword(basicAuthString);
            List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ADMIN"));
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password, authorities);
            if (this.basicAuthService.checkValidBasicAuth(basicAuthString)) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                SecurityContextHolder.getContext().setAuthentication(null);
            }
        }
        chain.doFilter(httpServletRequest, response);
    }
}
