package com.example.configurable.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by JeHuiPark on 2019-04-24
 * Blog   : https://jehuipark.github.io
 * Github : https://github.com/JeHuiPark
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  User findByUserId(String userId);
}
