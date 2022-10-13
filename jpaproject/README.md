
### 발생 에러 정리

#### ERROR1
원인 : org.h2.jdbc.JdbcSQLNonTransientConnectionException: Database may be already in use: null.   

해결 :    
url: jdbc:h2:file:./db/paydb;AUTO_SERVER=TRUE   
AUTO_SERVER=TRUE 추가 > file로 실행하는 경우 auto server 추가해야하고 memory로 실행할 경우 없어도 된다.   


sql 예약어 에러   
error message : Failed to execute SQL script statement #1 of URL   
원인 : insert into table ('column1') -> 컬럼명을 ''으로 사용    
(컬럼명은 ` 으로 사용해야한다.)   


#### ERROR1
Caused by: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name  
expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {}   

원인 : Test에서 @WebMvcTest 로 정의하면, 해당하는 layer에서만 테스트가 되기 때문에 controller안의 service는 bean구현이 안되서 발생하는 에러  

해결 : controller에서 필요한 bean은 test작성 시, @MockBean으로 꼭 선언해서 사용한다.  
