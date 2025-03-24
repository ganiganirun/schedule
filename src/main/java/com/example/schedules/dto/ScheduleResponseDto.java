package com.example.schedules.dto;

import com.example.schedules.entity.Schedule;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {

  private Long id;
  private String name;
  private String todo;
  private LocalDateTime updatedAt;

  public ScheduleResponseDto(Schedule schedule){
    this.id = schedule.getId();
    this.name = schedule.getName();
    this.todo = schedule.getTodo();
    this.updatedAt = schedule.getUpdatedAt();
  }


}
