package net.thumbtack.repo.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import net.thumbtack.model.Client;
import org.springframework.jdbc.core.RowMapper;

public class ClientMapper implements RowMapper<Client> {

  @Override
  public Client mapRow(ResultSet resultSet, int i) throws SQLException {
    Client client = new Client();
    client.setId(resultSet.getLong("id"));
    client.setLastName(resultSet.getString("lastName"));
    client.setFirstName(resultSet.getString("firstName"));
    client.setPatronymic(resultSet.getString("patronymic"));
    client.setEmail(resultSet.getString("email"));
    client.setAddress(resultSet.getString("postalAddress"));
    client.setPhone(resultSet.getString("phoneNumber"));
    client.setLogin(resultSet.getString("login"));
    client.setPassword(resultSet.getString("password"));
    return client;
  }
}
