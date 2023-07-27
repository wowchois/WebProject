
## Spring Test 

### @WebMvcTest   
: web에서 layer단위로 테스트할 경우 사용. (주로 Controller에서 사용)    
관련 layer에 대한 빈만 로드하지만 `@SpringBootTest` 경우 모든 빈을 로드해서 시간이 오래 걸린다.     
service, repository 연결 경우, `@MockBean`(가짜객체) 으로 주입받아 테스트한다.    

   
   
`MockMvc`으로 controller단위테스트에 사용. 


