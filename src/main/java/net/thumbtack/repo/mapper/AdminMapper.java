package net.thumbtack.repo.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import net.thumbtack.model.Admin;
import org.springframework.jdbc.core.RowMapper;

public class AdminMapper implements RowMapper<Admin> {

  @Override
  public Admin mapRow(ResultSet resultSet, int i) throws SQLException {
    Admin admin = new Admin();
    admin.setId(resultSet.getLong("id"));
    admin.setLastName(resultSet.getString("lastName"));
    admin.setFirstName(resultSet.getString("firstName"));
    admin.setPatronymic(resultSet.getString("patronymic"));
    admin.setPosition(resultSet.getString("position"));
    admin.setLogin(resultSet.getString("login"));
    admin.setPassword(resultSet.getString("password"));
    return admin;
  }

}
