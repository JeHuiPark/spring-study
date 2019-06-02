# java embedded database H2

## H2 데이터베이스 TCP 모드로 올리기

### TCP 모드의 장점
 - springboot db console 필요없음
 - database client를 이용하여 접속가능 (이기종 시스템 커넥션 가능)
 
### 의존성
```gradle
implementation 'com.h2database:h2'
```

#### 수정내역
  - 2019.06.02 tomcat datasource -> hikari datasource