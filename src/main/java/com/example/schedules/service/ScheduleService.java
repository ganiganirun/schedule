package com.example.schedules.service;

import com.example.schedules.dto.ScheduleRequestDto;
import com.example.schedules.dto.ScheduleResponseDto;
import com.example.schedules.entity.Schedule;
import java.util.List;

public interface ScheduleService {
  ScheduleResponseDto saveSchedule(ScheduleRequestDto dto);

  List<ScheduleResponseDto> findAllSchedules(String  updated_at, String  name);

  ScheduleResponseDto findScheduleById(Long id);

}
