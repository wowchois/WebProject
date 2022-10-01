## JWT 로그인

### JWT Interceptor 구현
: 모든 API 접근 전, preHandle로 token을 확인하는 Interceptor 구현.    

"Bearer " 토큰 확인.  
1. header에 토큰이 없거나 잘못된 토큰, 만료된 토큰 경우 response에 401 세팅, false 리턴    
2. 이외 토큰은 decode해서 user정보 request에 세팅, true 리턴.    

   
   
[구현 소스](./src/main/java/com/base/jpaproject/interceptor/UserAuthInterceptor.java)
