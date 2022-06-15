## 구현 내용 정리

### OAuth 로그인

#### Gradle

```xml
    implementation 'org.springframework.boot:spring-boot-starter-oauth2-client'
    implementation 'org.springframework.boot:spring-boot-starter-mustache'
```

#### 1. mustache로 로그인 구현

- 테스트로 controller 구현

```java
      @GetMapping("/")
      public String index() {
          return "index";
      }
```

- WebMvcConfig 설정

WebMvcConfigurer 로 .mustache 화면을 .html로 변경하는 작업 추가

- SecurityConfig 설정

uri마다 권한 설정 (ex : /admin 경우 "ROLE_ADMIN" 권한을 부여 - "ROLE_" 키워드로 권한 인식한다.)     


```java
    @EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
```

- securedEnabled :   
Secured 활성화 (메서드에 어노테이션으로 시큐리티 활성화 시킨다.)
- prePostEnabled :   
@PreAuthorize 활성화 (메서드 실행 직전에 시큐리티 활성화 시킨다.)   
                   @PostAuthorize 활성화 (메서드 실행 후에 시큐리티 활성화 시킨다.)  

(보통 @secured 많이 사용)





--------------------





## Excel 대용량 parsing
- ExcelController
- ExcelService
- util/ExcelSheetHandler  

swagger : /excel/parse  



