package com.example.schedules.repository;

import com.example.schedules.dto.ScheduleResponseDto;
import com.example.schedules.entity.Schedule;
import java.util.List;

public interface ScheduleRepository {
  ScheduleResponseDto saveSchedule(Schedule schedule);

  List<ScheduleResponseDto> findAllSchedules(String update_at, String name);

}
