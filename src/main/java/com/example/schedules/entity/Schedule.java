package com.example.schedules.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Schedule {

  private Long id;
  private String name;
  private String todo;
  private String createdAt;
  private String updatedAt;
  private String password;

  public Schedule(String name, String todo, String password, String createdAt, String updatedAt){
    this.name = name;
    this.todo = todo;
    this.password = password;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

//  public Schedule(String name, String todo, String updatedAt){
//    this.name = name;
//    this.todo = todo;
//    this.updatedAt = updatedAt;
//  }
//
//  public Schedule(String name, String todo, String password, String updatedAt){
//    this.name = name;
//    this.todo = todo;
//    this.password = password;
//    this.updatedAt = updatedAt;
//  }


}
