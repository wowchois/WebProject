## JPA

### join 종류 
1. 일반 join : 대상테이블에 join처럼 사용하는데, exists로만 사용된다.  
> join 테이블의 데이터는 못가져오고 포함여부만 확인해서 대상테이블 데이터만
필터한다.

2. fetch join : join으로 사용되며, join하는 테이블의 컬럼도 가져올 수 있다.   


jpa 에서는 insert into select 지원안함 -> querydsl로 해결해야함.


-----------------


### error정리

- sql 예약어 에러
> error message : Failed to execute SQL script statement #1 of URL

원인 : insert into table ('column1') -> 컬럼명을 ''으로 사용   
해결 : 컬럼명은 ` 으로 사용해야한다.  

- data.sql을 읽는데, jpa의 hibernate보다 먼저 sql을 실행해서 아래코드 정의해야 hibernate를 먼저 실행하고 db를 실행한다.   
(hibernate 초기화 전에 sql읽어서 발생)

```yml
  jpa:
    defer-datasource-initialization: true #hibernate을 db보다 먼저 시작
```

- h2 DB 연결 에러 
> error message : org.h2.jdbc.JdbcSQLNonTransientConnectionException: Database may be already in use: null.

해결 :   
```yml
url: jdbc:h2:file:./db/paydb;AUTO_SERVER=TRUE
```
AUTO_SERVER=TRUE 추가     
-> file로 실행하는 경우 auto server 추가해야하고 memory로 실행할 경우 없어도 된다.


