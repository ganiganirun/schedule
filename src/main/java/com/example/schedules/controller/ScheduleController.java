package com.example.schedules.controller;

import com.example.schedules.dto.ScheduleRequestDto;
import com.example.schedules.dto.ScheduleResponseDto;
import com.example.schedules.service.ScheduleService;
import java.time.LocalDate;
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

@RestController // @Controller + @ResponseBody
@RequestMapping("/schedules") // 들어온 요청을 특정 메서드와 매핑하기 위해 사용하는 어노테이션
public class ScheduleController {

  // 인스턴스 타입으로 선언하면 다른 구현체가 생길 경우 교체가 쉬움 그래서 인스턴스 타입으로 선언
  // 스프링이 알아서 ScheduleServiceImpl의 객체를 찾아서 주입해줌
  private final ScheduleService scheduleService;

  // 생성자 안만들어주면 위에 코드 오류남
  // 생성자를 통해 final 필드를 초기화해야 하기 때문에
  public ScheduleController(ScheduleService scheduleService) {
    this.scheduleService = scheduleService;
  }


  // 스케줄 저장 기능
  @PostMapping // Post작업을 수행하기 위한 Mapping
  public ResponseEntity<ScheduleResponseDto> saveSchedule(@RequestBody ScheduleRequestDto dto){

    return new ResponseEntity<>(scheduleService.saveSchedule(dto), HttpStatus.OK);

  }

  // 스케줄 날짜와 작성자를 통한 여러건 조회 기능
  @GetMapping
  public ResponseEntity<List<ScheduleResponseDto>> findAllSchedules(
      @RequestParam(required = false) LocalDate updated_at,
      @RequestParam(required = false) String name
  ){

    return new ResponseEntity<>(scheduleService.findAllSchedules(updated_at,name), HttpStatus.OK);
  }

  // 스케줄 단건 조회 기능
  @GetMapping("/{id}")
  public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long id){

    return new ResponseEntity<>(scheduleService.findScheduleById(id), HttpStatus.OK);
  }

  // 스케줄 수정 기능
  @PatchMapping("/{id}")
  public ResponseEntity<ScheduleResponseDto> updateScheduleById(
      @PathVariable Long id,
      @RequestBody ScheduleRequestDto dto
  ){
    return new ResponseEntity<>(scheduleService.updateScheduleById(id, dto.getName(),dto.getTodo(), dto.getPassword()), HttpStatus.OK);

  }

  // 스케줄 삭제 기능
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteSchedule(
      @PathVariable Long id,
      @RequestBody ScheduleRequestDto dto
  ){

    scheduleService.deleteSchedule(id, dto.getPassword());

    return new ResponseEntity<>(HttpStatus.OK);
  }

}
