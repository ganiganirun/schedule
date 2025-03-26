package com.example.schedules.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter // Getter 자동 생성 어노테이션
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자를 생성
public class Schedule {

  private Long id; // id
  private String name; // 작성자
  private String todo; // 할 일
  private String password; // 비밀번호
  private LocalDateTime createdAt; // 생성일
  private LocalDateTime updatedAt; // 수정일

  public Schedule(String name, String todo, String password){
    this.name = name;
    this.todo = todo;
    this.password = password;
    // LocalDateTime.now()로 스케줄 객체 생성 시 바로 초기화
    LocalDateTime now = LocalDateTime.now();
    this.createdAt = now;
    this.updatedAt = now;

  }
}
