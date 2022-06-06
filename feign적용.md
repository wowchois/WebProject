
### spring cloud release train   
: spring cloud에서 버전 호환을 위해 train으로 맞춘다.  

### Feign
: HTTP client binder (내부에서 rest api 호출이 가능하게 한다.)  


### Gradle 세팅
```

dependencyManagement {
    imports {
        mavenBom "org.springframework.cloud:spring-cloud-dependencies:Hoxton.SR8"  //boot:2.2, 2.3 경우 사용
        mavenBom "org.springframework.cloud:spring-cloud-dependencies:2021.0.0"   //boot:2.6 경우 사용
    }
}

dependencies {
  implementation 'org.springframework.cloud:spring-cloud-starter-openfeign'
  implementation 'org.springframework.cloud:spring-cloud-starter-netflix-hystrix:2.2.7.RELEASE' //버전명시는 boot:2.6, cloud:2020.0 이상 경우 사용
}
```

#### ERROR:Error creating bean with name 'configurationPropertiesBeans' defined in class path resource  
- spring boot 버전이 '2.6.2'인 경우 train버전을 Hoxton.SR8 또는 SR9 로 할 경우 에러가 발생한다.    
-> spring boot와 cloud 버전이 안맞아서 생겨서, 두 버전을 맞춰야 한다. (boot를 낮추거나, cloud를 올려서 해결)  
- 추가로 netflix-hystrix 경우, cloud 버전이 2020.0.2 이상 경우 버전을 명시해야 에러가 발생하지 않는다.   
-> :2.2.7.RELEASE 으로 버전 명시   

https://github.com/spring-cloud/spring-cloud-release/wiki/Spring-Cloud-2020.0-Release-Notes


### root application java

```
@EnableFeignClients //root에 feign 사용 선언
public class JpaprojectApplication {

}

```


### feign flient
: 호출할 url과 api를 정의하는 feignclient 파일을 생성.  
```

@FeignClient(name="testfeign", url="https://url.co.kr")
public interface TestClient {

    @GetMapping(value = "/test"
            , produces = "application/json")
    Map<String,Object> getTestApi();
}

```

