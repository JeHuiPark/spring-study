package com.example.configurable.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Created by JeHuiPark on 2019-05-25
 * Blog   : https://jehuipark.github.io
 * Github : https://github.com/JeHuiPark
 */
@Aspect
@Slf4j
public class UserInitAspect {

  @Before("execution(com.example.configurable.user.domain.User.new())")
  public void userInitBefore(){
    log.info("com.example.configurable.user.domain.User 생성전 ");
  }

  @After("execution(com.example.configurable.user.domain.User.new())")
  public void userInitAfter(){
    log.info("com.example.configurable.user.domain.User 생성후 ");
  }
}
