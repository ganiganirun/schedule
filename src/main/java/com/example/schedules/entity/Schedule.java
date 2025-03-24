package com.example.schedules.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Schedule {

  private Long id;
  private String name;
  private String todo;
  private String password;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public Schedule(String name, String todo, String password){
    this.name = name;
    this.todo = todo;
    this.password = password;
    //바로 LocalDateTime.now() 해주기
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();

  }
}
