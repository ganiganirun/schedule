package com.example.schedules.repository;

import com.example.schedules.dto.ScheduleResponseDto;
import com.example.schedules.entity.Schedule;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    parameters.put("created_at",schedule.getCreatedAt());
    parameters.put("updated_at",schedule.getUpdatedAt());

    Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
    return new ScheduleResponseDto(key.longValue(), schedule.getName(), schedule.getTodo(), schedule.getUpdatedAt());
  }

  @Override
  public List<ScheduleResponseDto> findAllSchedules(String update_at, String name) {

    return jdbcTemplate.query("select * from schedule where name = ? and left(updated_at, 10) >= ? order by updated_at desc", ScheduleRowMapper(), name, update_at);
  }

  @Override
  public Schedule findScheduleByIdOrElseThrow(Long id) {
    List<Schedule> result = jdbcTemplate.query("select * from schedule where id = ?",
        scheduleRowMapperV2(), id);
    return result.stream().findAny().orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id));
  }

  @Override
  public int updateScheduleById(Long id, String name, String todo, String updated_at, String password) {

    if(name != null && todo == null){
      return jdbcTemplate.update("update schedule set name = ?, updated_at = ? where id = ? and password = ?", name, updated_at, id, password);
    } else if (name == null && todo != null) {
      return jdbcTemplate.update("update schedule set todo = ? , updated_at = ? where id = ? and password = ?", todo, updated_at, id, password);
    }else {
      return jdbcTemplate.update("update schedule set name = ?, todo = ? , updated_at = ? where id = ? and password = ?", name, todo, updated_at, id, password);
    }
//    return jdbcTemplate.update("update schedule set name = ?, todo = ? , updated_at = ? where id = ? and password = ?", name, todo, updated_at, id, password);
  }

  @Override
  public int deleteSchedule(Long id, String password) {
    return jdbcTemplate.update("delete from schedule where id = ? and password = ?",id, password);
  }

//  @Override
//  public int updateScheduleName(Long id, String name, String updated_at, String password) {
//    return jdbcTemplate.update("update schedule set name = ? , updated_at = ? where id = ? and password = ?", name, updated_at, id, password);
//  }
//
//  @Override
//  public int updateScheduleTodo(Long id, String todo, String updated_at, String password) {
//    return jdbcTemplate.update("update schedule set  todo = ? , updated_at = ? where id = ? and password = ?", todo, updated_at, id, password);
//  }


  private RowMapper<ScheduleResponseDto> ScheduleRowMapper() {
    return new RowMapper<ScheduleResponseDto>() {
      @Override
      public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ScheduleResponseDto(
            rs.getLong("id"),
            rs.getString("name"),
            rs.getString("todo"),
            rs.getString("updated_at")
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
            rs.getString("created_at"),
            rs.getString("updated_at")
        );
      }
    };
  }


}
