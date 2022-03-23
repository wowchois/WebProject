package com.base.jpaproject.auth;

import com.base.jpaproject.main.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
@EnableWebSecurity //spring security 활성화
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
                .antMatchers("/super/**").hasRole(Role.SUPER.name())
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
