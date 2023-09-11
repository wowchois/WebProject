

## Excel 대용량 parsing
- ExcelController
- ExcelService
- util/ExcelSheetHandler
- util/SheetHandler : ExcelSheetHandler 개선

swagger : /excel/parse  


- 2만3천건 : xml파싱 read 774ms

### 문제점
이전에 구현한 ExcelSheetHandler 기능 : excel 전체 read 후 메모리에 저장해서 parsing 처리됨.    

발생되는 문제점    
1. cpu 과부하 : 현재 모든 cell,row 를 한번에 읽기 때문에 cpu가 급격히 증가.
2. OOM 발생 가능성 : 모든 데이터를 한번에 저장해서 처리되서 발생 가능성이 있음.


### 개선점
방법 1. 저장된 excel을 read하기 전에 여러 파일로 분리해서 저장 후 read   
방법 2. excel을 stream으로 읽으면서 row페이징 처리로 parsing.

-> 방법2로 개선 진행 중.

1. cpu 과부하 : excel stream parsing을 handler로 구현해서 sleep으로 과부하 방지.
2. OOM 발생 가능성 : 페이징 처리 구현. 개수 지정해서 DB적재 후 row 메모리 비우기.

### 고민한 부분
- handler로 read하고 stream 데이터를 각 서비스단에서 어떻게 구현할 지 고민.   
-> 각 서비스에서 excel 처리하는 서비스를 각각 구현. (excel서비스는 handler 상속)


### heap memory out error log
```console

java.util.concurrent.ExecutionException: java.lang.OutOfMemoryError: Java heap space
	at java.base/java.util.concurrent.FutureTask.report(FutureTask.java:122) ~[na:na]
	at java.base/java.util.concurrent.FutureTask.get(FutureTask.java:191) ~[na:na]
	at org.apache.coyote.AbstractProtocol.startAsyncTimeout(AbstractProtocol.java:634) ~[tomcat-embed-core-9.0.56.jar:9.0.56]
	at org.apache.coyote.AbstractProtocol.lambda$start$0(AbstractProtocol.java:618) ~[tomcat-embed-core-9.0.56.jar:9.0.56]
	at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:539) ~[na:na]
	at java.base/java.util.concurrent.FutureTask.runAndReset(FutureTask.java:305) ~[na:na]
	at java.base/java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.run(ScheduledThreadPoolExecutor.java:305) ~[na:na]
	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1136) ~[na:na]
	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:635) ~[na:na]
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61) ~[tomcat-embed-core-9.0.56.jar:9.0.56]
	at java.base/java.lang.Thread.run(Thread.java:833) ~[na:na]
Caused by: java.lang.OutOfMemoryError: Java heap space

...

java.sql.SQLException: Java heap space
	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:129) ~[mysql-connector-java-8.0.27.jar:8.0.27]

...

2023-09-12 00:49:13.949  WARN 9436 --- [nio-8080-exec-8] o.h.engine.jdbc.spi.SqlExceptionHelper   : SQL Error: 0, SQLState: HY001
2023-09-12 00:49:13.949 ERROR 9436 --- [nio-8080-exec-8] o.h.engine.jdbc.spi.SqlExceptionHelper   : Java heap space
2023-09-12 00:49:13.950 ERROR 9436 --- [nio-8080-exec-9] o.a.c.c.C.[.[.[/].[dispatcherServlet]    : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Handler dispatch failed; nested exception is java.lang.OutOfMemoryError: Java heap space] with root cause

java.lang.OutOfMemoryError: Java heap space

...

```

  
