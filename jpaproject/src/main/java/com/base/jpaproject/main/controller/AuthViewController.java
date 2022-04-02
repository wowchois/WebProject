package com.base.jpaproject.main.controller;

import com.base.jpaproject.main.dto.SessionUser;
import com.base.jpaproject.main.entity.AdminUser;
import com.base.jpaproject.main.entity.Role;
import com.base.jpaproject.main.repository.AdminUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AuthViewController {

    private final AdminUserRepository adminUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    //private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/loginform")
    public String loginForm() {
        return "loginForm";
    }

    @GetMapping("/joinform")
    public String joinForm() {
        return "joinForm";
    }

    @PostMapping("/join")
    public @ResponseBody String join(AdminUser user) {
        //password암호화 안되어있으면  security 로그인이 불가능
        String encPwd = bCryptPasswordEncoder.encode(user.getPassword());

        user.setPassword(encPwd);
        user.setRole(Role.GUEST);

        adminUserRepository.save(user);
        
        return "join";
    }

    @GetMapping("/user")
    public @ResponseBody  String userAuth(){
        return "user";
    }

    @GetMapping("/manager")
    public @ResponseBody  String managerAuth(){
        return "manager";
    }

    @GetMapping("/admin")
    public @ResponseBody  String adminAuth(){
        return "admin";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/userinfo")
    public @ResponseBody  String userInfo(){
        return "user info";
    }
}
