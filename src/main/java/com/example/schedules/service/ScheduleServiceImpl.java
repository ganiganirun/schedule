package com.example.schedules.service;

import com.example.schedules.dto.ScheduleRequestDto;
import com.example.schedules.dto.ScheduleResponseDto;
import com.example.schedules.entity.Schedule;
import com.example.schedules.repository.ScheduleRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ScheduleServiceImpl implements ScheduleService{

  private final ScheduleRepository scheduleRepository;

  public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
    this.scheduleRepository = scheduleRepository;
  }


  @Override
  public ScheduleResponseDto saveSchedule(ScheduleRequestDto dto) {

    Schedule schedule = new Schedule(dto.getName(), dto.getTodo(), dto.getPassword());
    return scheduleRepository.saveSchedule(schedule);
  }

  @Override
  public List<ScheduleResponseDto> findAllSchedules(LocalDate updated_at, String name) {

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
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The password is a required value.");
    }

    int updateeRow = scheduleRepository.updateScheduleById(id, name, todo, password);

    if(updateeRow == 0){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
    }

    Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);

    return new ScheduleResponseDto(schedule);
  }

  @Override
  public void deleteSchedule(Long id, String password) {

    if (password == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The password is a required value.");
    }

    int deletedRow = scheduleRepository.deleteSchedule(id ,password);

    if(deletedRow == 0){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Does not exist id = " + id);
    }
  }

}
