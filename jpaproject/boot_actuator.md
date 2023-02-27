# Spring Boot Actuator
: application 내부와 작동방법을 확인 할 수 있다.    
(ex : docker에서 실행 중인 application의 yml 설정파일 등을 볼 수 있다.)    


### dependency
```gradle
implementation 'org.springframework.boot:spring-boot-starter-actuator'
```

### application.yml
: web에서 확인할 수 있는 기본경로 세팅  

``` yml
management:
  endpoints:
    web:
      base-path: /mg

```
