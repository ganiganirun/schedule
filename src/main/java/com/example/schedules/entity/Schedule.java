package com.example.schedules.entity;

import java.sql.Timestamp;
import java.time.Clock;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import org.springframework.data.relational.core.mapping.Column;

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
    this.createdAt = localDateTime();
    this.updatedAt = localDateTime();

  }

  private LocalDateTime localDateTime(){
    LocalDateTime now = LocalDateTime.now(Clock.systemDefaultZone());

    return now;
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
