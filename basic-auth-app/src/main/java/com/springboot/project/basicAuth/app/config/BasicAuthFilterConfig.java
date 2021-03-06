package com.springboot.project.basicAuth.app.config;

import com.springboot.project.basicAuth.app.filter.BasicAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BasicAuthFilterConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BasicAuthFilter basicAuthFilter;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();

        http.headers().frameOptions().disable();

        http.authorizeRequests()
                .antMatchers("/v1/cipher/**", "/v1/auth/**", "/h2/**")
                .permitAll()
                .and()
                .addFilterBefore(basicAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/**")
                .hasAnyAuthority("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

}
