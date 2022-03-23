package com.base.jpaproject.auth;

import com.base.jpaproject.main.entity.AdminUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/*
security가 /login 주소로 로그인 실행하면서 security session이 생성된다.
security session에 Authentication 타입 객체로 세션이 저장됨.
 -> Authentication 객체 안에 user정보가 저장되어 있는데, 그게 UserDetails 타입 객체이다.
 (UserDetails의 user정보를 사용하기 위해 implements한다.)
 */
@RequiredArgsConstructor
public class PrincipleDetails implements UserDetails {

    private final AdminUser user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> grantCollect = new ArrayList<>();
        grantCollect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRoleLevel();
            }
        });
        return grantCollect;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    //계정 만료 여부
    @Override
    public boolean isAccountNonExpired() {
        return true; //NO
    }

    //계정 잠김 여부
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //비밀번호 기간 유효 여부
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //계정 사용 여부
    @Override
    public boolean isEnabled() {
        //1년간 로그인 안한 회원은 휴면계정 전환한다면 현재시간 - 로그인시간 = 1년지난경우 로 처리
        return true;
    }
}







