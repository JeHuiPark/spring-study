package com.example.h2.user.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Entity
public class User {

  @Id
  @GeneratedValue
  private long id;
  @Column(unique = true)
  private String userId;
  private String userName;
  private int age;
}
