package com.example.configurable;

import com.example.configurable.user.domain.User;
import com.example.configurable.user.domain.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by JeHuiPark on 2019-04-25
 * Blog   : https://jehuipark.github.io
 * Github : https://github.com/JeHuiPark
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

  @Autowired
  UserRepository userRepository;

  @Test
  public void contextLoadTest() {
    User user = new User();
    Assert.assertNotNull(user.getUserRepository());
  }

  @Test
  public void ltwTest(){
    final String userId = "아이디";
    User user  = new User();
    user.setUserId(userId);
    user.setUserNm("박제희");
    user.setStatus("9");
    userRepository.saveAndFlush(user);

    User persistUser = userRepository.findByUserId(userId);
    persistUser.statusChange("3");
    User test = userRepository.findByUserId(userId);
    assert "3".equals(test.getStatus());
  }
}