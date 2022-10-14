
### entity
@GeneratedValue(strategy = GenerationType.IDENTITY)    
: `IDENTITY`는 insert시, auto_incre로 생성되는 id 제외하고 실행한다.    
`AUTO`경우 default로, id max값을 select > hibernate에서 id update > insert쿼리 실행 과정이 있다.
