package com.example.configurable.user.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;

/**
 * Created by JeHuiPark on 2019-04-24
 * Blog   : https://jehuipark.github.io
 * Github : https://github.com/JeHuiPark
 */
@Configurable(autowire = Autowire.BY_TYPE)
@Getter@Setter
@Entity
@Slf4j
public class User {

  @Transient
  private  UserRepository userRepository;

  public void setUserRepository(UserRepository userRepository){
    this.userRepository = userRepository;
    log.info("Auto Dependency Injection");
  }

  @Id
  @GeneratedValue
  private long id;
  private String userId;
  private String userNm;
  private String status;

  /**
   * 스테이터스 값 변경
   * @param status
   */
  public void statusChange(String status){
    log.info("status origin = {}, status new = {} ", this.status, status);
    this.status = status;
    userRepository.saveAndFlush(this);
  }
}
