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




}
