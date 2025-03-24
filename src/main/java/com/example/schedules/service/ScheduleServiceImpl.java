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
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ScheduleServiceImpl implements ScheduleService{

  private final ScheduleRepository scheduleRepository;

  public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
    this.scheduleRepository = scheduleRepository;
  }

  public String dateFormat(){
    Date today = new Date();

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return dateFormat.format(today);
  }


  @Override
  public ScheduleResponseDto saveSchedule(ScheduleRequestDto dto) {

    Schedule schedule = new Schedule(dto.getName(), dto.getTodo(), dto.getPassword(), dateFormat(), dateFormat());
    return scheduleRepository.saveSchedule(schedule);
  }

  @Override
  public List<ScheduleResponseDto> findAllSchedules(String updated_at, String name) {

    return scheduleRepository.findAllSchedules(updated_at, name);
  }

  @Override
  public ScheduleResponseDto findScheduleById(Long id) {

    Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);

    return new ScheduleResponseDto(schedule);
  }

  @Transactional
  @Override
  public ScheduleResponseDto updateScheduleById(Long id, String name, String todo, String password) {

    if (password == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The title is a required value.");
    }

    int updateeRow = scheduleRepository.updateScheduleById(id, name, todo, dateFormat(), password);

    if(updateeRow == 0){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
    }

    Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);

    return new ScheduleResponseDto(schedule);
  }

//  @Override
//  public ScheduleResponseDto updateScheduleName(Long id, String name, String password) {
//    int updateeRow = scheduleRepository.updateScheduleName(id, name, dateFormat(), password);
//
//    if(updateeRow == 0){
//      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
//    }
//
//    Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);
//
//    return new ScheduleResponseDto(schedule);
//  }
//
//  @Override
//  public ScheduleResponseDto updateScheduleTodo(Long id, String todo, String password) {
//    int updateeRow = scheduleRepository.updateScheduleTodo(id, todo, dateFormat(), password);
//
//    if(updateeRow == 0){
//      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
//    }
//
//    Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);
//
//    return new ScheduleResponseDto(schedule);
//  }


}
