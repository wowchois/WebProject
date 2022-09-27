package com.base.jpaproject.interceptor;

import com.base.jpaproject.entity.AuthorizedUser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Base64;
import java.util.Date;
import java.util.Base64.Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class UserAuthInterceptor implements HandlerInterceptor {

    private final String TOEKN_PREFIX = "Bearer ";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String auth = request.getHeader(HttpHeaders.AUTHORIZATION);

        //토큰 없는 경우
        if(auth == null || !auth.startsWith(TOEKN_PREFIX)){
            setResponseNoAuth(response);
        }else{
            //토큰 만료/올바르지않은 토큰
            Decoder decode = Base64.getDecoder();
            try{
                String te = new String(decode.decode(auth.replace(TOEKN_PREFIX, "").getBytes()));
                ObjectMapper om = new ObjectMapper();
                AuthorizedUser user = om.readValue(te, AuthorizedUser.class);

                System.out.println("### user id : " + user.getId());
                System.out.println("### user id : " + user.getExpire());

                if(isTokenExpire(user.getExpire())){
                    setResponseNoAuth(response);
                }else{
                    request.setAttribute("user", user.getId());
                }
            }catch(JsonParseException e){
                setResponseNoAuth(response);
            }
        }

        return true;
    }

    private void setResponseNoAuth(HttpServletResponse response){
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
    }

    private Boolean isTokenExpire(long tokenDate){
        Date date = new Date();
        long unixNowTime = date.getTime() / 1L;
        System.out.println("///// "+unixNowTime);

        long compare = unixNowTime - tokenDate;

        return compare > 0 ? true : false;
    }

}
