package com.example.schedules.controller;


import com.example.schedules.dto.ScheduleRequestDto;
import com.example.schedules.dto.ScheduleResponseDto;
import com.example.schedules.service.ScheduleService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

  private final ScheduleService scheduleService;

  public ScheduleController(ScheduleService scheduleService) {
    this.scheduleService = scheduleService;
  }


  @PostMapping
  public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto dto){

    return new ResponseEntity<>(scheduleService.saveSchedule(dto), HttpStatus.OK);

  }

  @GetMapping
  public ResponseEntity<List<ScheduleResponseDto>> findAllSchedules(
      @RequestParam String updated_at,
      @RequestParam String name
  ){

    return new ResponseEntity<>(scheduleService.findAllSchedules(updated_at,name), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long id){

    return new ResponseEntity<>(scheduleService.findScheduleById(id), HttpStatus.OK);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<ScheduleResponseDto> updateScheduleById(
      @PathVariable Long id,
      @RequestBody ScheduleRequestDto dto
  ){
    return new ResponseEntity<>(scheduleService.updateScheduleById(id, dto.getName(),dto.getTodo(), dto.getPassword()), HttpStatus.OK);

  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteSchedule(
      @PathVariable Long id,
      @RequestBody ScheduleRequestDto dto
  ){

    scheduleService.deleteSchedule(id, dto.getPassword());

    return new ResponseEntity<>(HttpStatus.OK);
  }

//  @PatchMapping("/{id}")
//  public ResponseEntity<ScheduleResponseDto> updateScheduleName(
//      @PathVariable Long id,
//      @RequestBody ScheduleRequestDto dto
//  ){
//    return new ResponseEntity<>(scheduleService.updateScheduleName(id, dto.getName(), dto.getPassword()), HttpStatus.OK);
//
//  }
//
//  @PatchMapping("/{id}")
//  public ResponseEntity<ScheduleResponseDto> updateScheduleTodo(
//      @PathVariable Long id,
//      @RequestBody ScheduleRequestDto dto
//  ){
//    return new ResponseEntity<>(scheduleService.updateScheduleTodo(id, dto.getTodo(), dto.getPassword()), HttpStatus.OK);
//
//  }


}
