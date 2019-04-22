package com.example.h2.user.infrastructure;

import com.example.h2.user.domain.User;
import com.example.h2.user.domain.UserRepository;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserRegister {

  private final UserRepository userRepository;

  /**
   * 랜덤유저생성
   * @return 생성된 유저
   */
  public User randUser(){
    User rand = User.builder()
        .userId(System.currentTimeMillis() + "")
        .age(new Random().nextInt(31)+1)
        .userName(new String[]{"김철수", "김영희", "짱구", "맹구", "슛돌이", "도꺠비", "김삿갓"}[new Random().nextInt(7)])
        .build();
    return userRepository.save(rand);
  }
}
