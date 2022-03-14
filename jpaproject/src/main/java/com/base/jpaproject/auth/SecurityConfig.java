package com.base.jpaproject.auth;

import com.base.jpaproject.main.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity //spring security 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers("/","/login/**","/css/**","/js/**","/images/**").permitAll()
                .antMatchers("/api/").hasRole(Role.SUPER.name())
                .anyRequest().authenticated()
                .and()
                .logout().logoutSuccessUrl("/")
                .and()
                .oauth2Login()
                .authorizationEndpoint()
                .baseUri("/oauth2/authorization")
                .and()
                .userInfoEndpoint().userService(customOAuth2UserService)
                ;
    }

}
