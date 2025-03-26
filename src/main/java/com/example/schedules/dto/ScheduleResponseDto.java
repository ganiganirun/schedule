package com.example.schedules.dto;

import com.example.schedules.entity.Schedule;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter // 각 속성에 getter 생성
@AllArgsConstructor // 각 파라미터를 모두 포함한 생성자 생성
public class ScheduleResponseDto {

  // Response 되는 값을 생각하면 id, name, todo, updatedAt 만 있으면 됨
  //  password는 보여주면 안되고 처음 저장했을 때 어차피 createdAt 이랑 updatedAt 은 같은 값
  //  우리는 수정하고 난 뒤에 수정된 날짜를 보여줘야하니까 굳이 createdAt은 필요하지 않음

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
