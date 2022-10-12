
## H2 DB사용
- 시작
: 처음 시작 시, embeded 환경에서 `jdbc:h2:~/DB명` 으로 로컬DB를 생성 요청 한다. (id/pwd 변경없음)  


- TCP환경
: 생성한 로컬DB에 tcp로 접속.   
`jdbc:h2:tcp://localhost/~/DB명` 으로 접속   

- In Memory
: 메모리에서 접속.  
`jdbc:h2:mem:./db/DB명` mem뒤에는 DB저장할 장소.  

### option
- `MODE=MySQL`
