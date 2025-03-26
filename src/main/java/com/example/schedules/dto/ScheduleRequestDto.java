package com.example.schedules.dto;

import lombok.Getter;

@Getter
public class ScheduleRequestDto {

  // Schedule 에서 createdAd 이랑 updatedAt LocalDateTime.now()로 초기화 해주니까 굳이 request 값에 추가할 필요 없음
  // 우리가 받아와야 할 값은 name, todo, password

  private String name;
  private String todo;
  private String password;

}
