package com.example.schedules.repository;

import com.example.schedules.dto.ScheduleResponseDto;
import com.example.schedules.entity.Schedule;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

@Repository
public class JdbcTemplateScheduleRepository implements ScheduleRepository{

  private final JdbcTemplate jdbcTemplate;

  public JdbcTemplateScheduleRepository(DataSource dateSource) {
    this.jdbcTemplate = new JdbcTemplate(dateSource);
  }


  @Override
  public ScheduleResponseDto saveSchedule(Schedule schedule) {
    SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
    jdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("id");

    Map<String, Object> parameters = new HashMap<>();

    parameters.put("name" , schedule.getName());
    parameters.put("todo", schedule.getTodo());
    parameters.put("password", schedule.getPassword());
    parameters.put("created_at", schedule.getCreatedAt());
    parameters.put("updated_at", schedule.getUpdatedAt());


    Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
    return new ScheduleResponseDto(key.longValue(), schedule.getName(), schedule.getTodo(), schedule.getUpdatedAt());
  }

  @Override
  public List<ScheduleResponseDto> findAllSchedules(LocalDate update_at, String name) {
    if(update_at != null && name == null){
      return jdbcTemplate.query("select * from schedule where left(updated_at, 10) >= ? order by updated_at desc", ScheduleRowMapper(), update_at);
    } else if (update_at == null && name != null) {
      return jdbcTemplate.query("select * from schedule where name = ? order by updated_at desc", ScheduleRowMapper(), name);
    }else if (update_at == null && name == null){
      return jdbcTemplate.query("select * from schedule order by updated_at desc", ScheduleRowMapper());
    } else {
      return jdbcTemplate.query("select * from schedule where name = ? and left(updated_at, 10) >= ? order by updated_at desc", ScheduleRowMapper(), name, update_at);
    }
  }

  @Override
  public Schedule findScheduleByIdOrElseThrow(Long id) {
    List<Schedule> result = jdbcTemplate.query("select * from schedule where id = ?",
        scheduleRowMapperV2(), id);
    return result.stream().findAny().orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id));
  }

  @Override
  public int updateScheduleById(Long id, String name, String todo, String password) {

    // 내가 하나만 바꿔도 프론트에서 싹다 보내줄거라는 가정 하에 굳이 if문으로 경우를 나누지 않아도 괜찮고 다음 sql 문 사용해도 됨(튜터님 께 질문 후 수정한 부분)
    return jdbcTemplate.update("update schedule set name = ?, todo = ? where id = ? and password = ?", name, todo, id, password);
  }

  @Override
  public int deleteSchedule(Long id, String password) {
    return jdbcTemplate.update("delete from schedule where id = ? and password = ?",id, password);
  }


  private RowMapper<ScheduleResponseDto> ScheduleRowMapper() {
    return new RowMapper<ScheduleResponseDto>() {
      @Override
      public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ScheduleResponseDto(
            rs.getLong("id"),
            rs.getString("name"),
            rs.getString("todo"),
            timeStampToLocalDateTime(rs.getTimestamp("updated_at"))
        );
      }
    };
  }

  private RowMapper<Schedule> scheduleRowMapperV2(){
    return new RowMapper<Schedule>() {
      @Override
      public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {

        return new Schedule(
            rs.getLong("id"),
            rs.getString("name"),
            rs.getString("todo"),
            rs.getString("password"),
            timeStampToLocalDateTime(rs.getTimestamp("created_at")),
            timeStampToLocalDateTime(rs.getTimestamp("updated_at"))
        );
      }
    };
  }

  private LocalDateTime timeStampToLocalDateTime(Timestamp t){

    return t.toLocalDateTime();
  }


}
