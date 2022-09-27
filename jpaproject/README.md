
### 발생 에러 정리

원인 : org.h2.jdbc.JdbcSQLNonTransientConnectionException: Database may be already in use: null.   

해결 :    
url: jdbc:h2:file:./db/paydb;AUTO_SERVER=TRUE   
AUTO_SERVER=TRUE 추가 > file로 실행하는 경우 auto server 추가해야하고 memory로 실행할 경우 없어도 된다.   


sql 예약어 에러   
error message : Failed to execute SQL script statement #1 of URL   
원인 : insert into table ('column1') -> 컬럼명을 ''으로 사용    
(컬럼명은 ` 으로 사용해야한다.)   
