# OAuth 로그인

### Gradle

    implementation 'org.springframework.boot:spring-boot-starter-oauth2-client'
    implementation 'org.springframework.boot:spring-boot-starter-mustache'

### 1. mustache로 로그인 구현

- 테스트로 controller 구현

      @GetMapping("/")
      public String index() {
          return "index";
      }

- WebMvcConfig 설정

WebMvcConfigurer 로 .mustache 화면을 .html로 변경하는 작업 추가

- SecurityConfig 설정

uri마다 권한 설정 (ex : /admin 경우 "ROLE_ADMIN" 권한을 부여 - "ROLE_" 키워드로 권한 인식한다.)
