package com.example.schedules.repository;

import com.example.schedules.dto.ScheduleResponseDto;
import com.example.schedules.entity.Schedule;
import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository {
  ScheduleResponseDto saveSchedule(Schedule schedule);

  List<ScheduleResponseDto> findAllSchedules(LocalDate update_at, String name);

  Schedule findScheduleByIdOrElseThrow(Long id);

  int updateScheduleById(Long id, String name, String todo, String password);

  int deleteSchedule(Long id, String password);

//  int updateScheduleName(Long id, String name, String updated_at, String password);
//
//  int updateScheduleTodo(Long id, String todo, String updated_at, String password);

}
