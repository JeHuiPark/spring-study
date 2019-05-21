# Load Time Weaver 적용


## 2019.05.21
  **LTW를 적용하여 new 연산자를 이용하여 생성된 객체도 IOC 대상에 포함? 아니 약간 다른 개념인가 ..? **
  문제점: 위빙하는 과정에서 경고발생 함, 위빙옵션 설정 필요한듯.. spring-data 패키지와 충돌?
    `warning javax.* types are not being woven because the weaver option '-Xset:weaveJavaxPackages=true' has not been specified`
    
    ``` console
     [Xlint:cantFindType]
    [AppClassLoader@73d16e93] error can't determine implemented interfaces of missing type com.querydsl.core.types.EntityPath
    when weaving type org.springframework.data.querydsl.SimpleEntityPathResolver
    when weaving classes 
    when weaving 
    ```
  
  LTW는 의존성 주입만으로 동작하지 않기 때문에 실행시 jvmargs agent 옵션추가
  
  - ide run: run/debug configuration에 직접 jar경로 지정
  - script run: 아래 스크립트 추가 하여 jvm 인자 전달
    ```gradle
    test.doFirst {
        jvmArgs "-javaagent:${ltwPath}"
    }
    
    bootRun.doFirst {
        jvmArgs "-javaagent:${ltwPath}"
    }
    ```
  
  
  
