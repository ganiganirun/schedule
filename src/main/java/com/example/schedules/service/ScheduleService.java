package com.example.schedules.service;

import com.example.schedules.dto.ScheduleRequestDto;
import com.example.schedules.dto.ScheduleResponseDto;
import com.example.schedules.entity.Schedule;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleService {
  ScheduleResponseDto saveSchedule(ScheduleRequestDto dto);

  List<ScheduleResponseDto> findAllSchedules(LocalDate updated_at, String  name);

  ScheduleResponseDto findScheduleById(Long id);

  ScheduleResponseDto updateScheduleById(Long id, String name, String todo, String password);

  void deleteSchedule(Long id, String password);

}
