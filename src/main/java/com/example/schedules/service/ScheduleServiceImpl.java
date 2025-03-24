package com.example.schedules.service;

import com.example.schedules.dto.ScheduleRequestDto;
import com.example.schedules.dto.ScheduleResponseDto;
import com.example.schedules.entity.Schedule;
import com.example.schedules.repository.ScheduleRepository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl implements ScheduleService{

  private final ScheduleRepository scheduleRepository;

  public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
    this.scheduleRepository = scheduleRepository;
  }


  @Override
  public ScheduleResponseDto saveSchedule(ScheduleRequestDto dto) {
    Date today = new Date();

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    Schedule schedule = new Schedule(dto.getName(), dto.getTodo(), dto.getPassword(), dateFormat.format(today), dateFormat.format(today));
    return scheduleRepository.saveSchedule(schedule);
  }



}
