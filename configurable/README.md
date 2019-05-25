# Load Time Weaver 적용


## 2019.05.25 Updated
  **LTW를 적용하여 ~~new 연산자를 이용하여 생성된 객체도 IOC 대상에 포함?~~ 아니 약간 다른 개념인가 ..? **
  > IOC 대상에 포함되진 않는다. 즉, 객체에 대한 제어는 프레임 워크가 아닌 개발자가 직접한다.
  다만, @Configurable 어노테이션이 달린 클래스는 로드타임에 위빙되어 new 연산자를 통해 객체화 되는 시점에 스프링의 AOP 모듈에 의해 bean 팩토리로부터 DI(Dependency Injection)받는게 가능해진다.
  절대 스프링의 bean과 헷갈려서는 안된다.
  
  
  실제 DI 받는 과정
  ![image](https://user-images.githubusercontent.com/25237661/58366604-cc47b800-7f0f-11e9-9f9c-d22e6a9846e8.png)
  
  
  문제점: 위빙하는 과정에서 경고발생, 위빙옵션 설정 필요한듯.. spring-data 패키지와 충돌?
    `warning javax.* types are not being woven because the weaver option '-Xset:weaveJavaxPackages=true' has not been specified`
    
    ``` console
     [Xlint:cantFindType]
    [AppClassLoader@73d16e93] error can't determine implemented interfaces of missing type com.querydsl.core.types.EntityPath
    when weaving type org.springframework.data.querydsl.SimpleEntityPathResolver
    when weaving classes 
    when weaving 
    ```
    
  **solution**
  이에 대한 해답은 @EnableLoadTimeWeaving 어노테이션에 있었음
  classpath에 META-INF/aop.xml 아래와 같이 작성
  
  ```xml
  <aspectj>
    <weaver options="-Xset:weaveJavaxPackages=true" >
      <include within="com.example.configurable..*"/>
    </weaver>
  </aspectj>
  ```
  
  LTW는 의존성 추가만으로 동작하지 않기 때문에 실행시 jvmargs agent 옵션추가
  
  - ide run: run/debug configuration에 직접 jar경로 지정
  - script run: 아래 스크립트 추가 하여 jvm 인자 전달
    ```gradle
    test.doFirst {
        jvmArgs "-javaagent:${ltwPath}"
    }
    
    bootRun.doFirst {
        jvmArgs "-javaagent:${ltwPath}"
    }
    File instrumentLibPath(){
        return sourceSets.getByName("main").compileClasspath.find {
            cls -> return cls.getName().contains("spring-instrument")
        }
    }
    ```
