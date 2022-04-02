package com.base.jpaproject.auth;

import com.base.jpaproject.main.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
@EnableWebSecurity //spring security 활성화
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
//securedEnabled : @Secured 활성화(메서드에 어노테이션으로 시큐리티 활성화 시킨다.)
//prePostEnabled : @PreAuthorize 활성화(메서드 실행 직전에 시큐리티 활성화 시킨다.)
//                  @PostAuthorize 활성화(메서드 실행 후에 시큐리티 활성화 시킨다.)
//보통 @secured 많이 쓴다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    //해당 bean을 IoC로 등록
    @Bean
    public BCryptPasswordEncoder encodePwd(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                //.formLogin().disable()
                //.httpBasic().disable()
                .authorizeRequests()
                //.antMatchers("/swagger-ui/**").denyAll()
                //.antMatchers("/","/login","/css/**","/js/**","/images/**").permitAll()
                .antMatchers("/user/**").authenticated() //인증만되면 접근가능
                .antMatchers("/manager/**").access("hasRole('MANAGER') or hasRole('ADMIN')")
                .antMatchers("/admin/**").hasRole(Role.ADMIN.name())
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/loginform") //403권한제한 경우 로그인페이지로 이동
                .usernameParameter("username") //view단의 username과 loadUserByUsername username이 일치해야 해서 선언!
                .loginProcessingUrl("/login") // /login 주소로 security login 동작 (/login 컨트롤러가 필요없음)
                .defaultSuccessUrl("/")
                //.anyRequest().authenticated()
                /*
                .and()
                .logout().logoutSuccessUrl("/")
                .and()
                .oauth2Login()
                .authorizationEndpoint()
                .baseUri("/oauth2/authorization")
                .and()
                .userInfoEndpoint().userService(customOAuth2UserService)
                 */
                ;
    }

}
