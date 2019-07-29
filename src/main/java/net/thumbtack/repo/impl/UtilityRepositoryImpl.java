package net.thumbtack.repo.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.thumbtack.dto.LogInDto;
import net.thumbtack.exception.ErrorList;
import net.thumbtack.exception.MyError;
import net.thumbtack.model.Admin;
import net.thumbtack.model.Client;
import net.thumbtack.repo.iface.UtilityRepository;
import net.thumbtack.repo.mapper.AdminMapper;
import net.thumbtack.repo.mapper.ClientMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UtilityRepositoryImpl implements UtilityRepository {

  private JdbcTemplate jdbcTemplate;

  public UtilityRepositoryImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public Object logIn(LogInDto logInDto) {
    Object response = null;
    List<MyError> errors = new ArrayList<>();
    List<String > loginsAdmin = new ArrayList<>();
    List<Map<String, Object>> rowsAdmin = jdbcTemplate.queryForList("SELECT login FROM admins");
    rowsAdmin.forEach(row -> {
      String login = (String) row.get("login");
      loginsAdmin.add(login);
    });
    List<String > loginsClient = new ArrayList<>();
    List<Map<String, Object>> rowsClient = jdbcTemplate.queryForList("SELECT login FROM clients");
    rowsClient.forEach(row -> {
      String login = (String) row.get("login");
      loginsClient.add(login);
    });

    if(loginsAdmin.contains(logInDto.getLogin())) {
      response = (Admin)jdbcTemplate.queryForObject("SELECT * FROM admins WHERE login = ?",
          new Object[]{logInDto.getLogin()}, new AdminMapper());
    }
    if(loginsClient.contains(logInDto.getLogin())) {
      response = (Client)jdbcTemplate.queryForObject("SELECT * FROM clients WHERE login = ?",
          new Object[]{logInDto.getLogin()}, new ClientMapper());
    }
    if(!loginsAdmin.contains(logInDto.getLogin()) && !loginsClient.contains(logInDto.getLogin())){
      errors.add(new MyError("WRONG_LOGIN",
          "login", "Нет пользователя с таким логином."));
      response = new ErrorList(errors);
    }
    return response;
  }

  @Override
  public Object getAccaunt(String role, long id) {
    Object response = null;
    if(role.equals("admin")) {
      response = (Admin)jdbcTemplate.queryForObject("SELECT * FROM admins WHERE id = ?",
          new Object[]{id}, new AdminMapper());
    }
    if(role.equals("client")) {
      response = (Client)jdbcTemplate.queryForObject("SELECT * FROM clients WHERE id = ?",
          new Object[]{id}, new ClientMapper());
    }
    return response;
  }
}
