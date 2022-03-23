package com.base.jpaproject.auth;

import com.base.jpaproject.main.entity.AdminUser;
import com.base.jpaproject.main.repository.AdminUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//Authentication 객체 구현
/*
/login 요청이 오면 자동으로 UserDetailsService 타입으로 loadUserByUsername 함수가 실행된다.
실행순서 : /login -> Ioc컨테이너에서 UserDetailsService의 loadUserByUsername 호출(param:username)
         -> loadUserByUsername에서 DB에서 찾은 UserDetails(username) 리턴
         -> security session (내부 Authentication(내부 UserDetails(username)))
 */
@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private AdminUserRepository userRepository;

    //
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AdminUser userEntity = userRepository.findByUsername(username);
        if(userEntity != null){
            return new PrincipleDetails(userEntity);
        }
        return null;
    }
}




