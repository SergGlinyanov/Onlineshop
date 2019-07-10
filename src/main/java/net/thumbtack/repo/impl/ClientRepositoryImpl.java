package net.thumbtack.repo.impl;

import net.thumbtack.model.Client;
import net.thumbtack.repo.iface.ClientRepository;
import net.thumbtack.repo.mapper.ClientMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class ClientRepositoryImpl implements ClientRepository {

  private JdbcTemplate jdbcTemplate;
  private ClientMapper clientMapper;

  public ClientRepositoryImpl(JdbcTemplate jdbcTemplate, ClientMapper clientMapper) {
    this.jdbcTemplate = jdbcTemplate;
    this.clientMapper = clientMapper;
  }

  @Override
  public void addClient(Client client) {
    jdbcTemplate.update("INSERT INTO clients (lastName,firstName,patronymic,email,"
            + "postalAddress,phoneNumber,login,password) values (?,?,?,?,?,?,?,?)",
        client.getLastName(),
        client.getFirstName(),
        client.getPatronymic(),
        client.getEmail(),
        client.getAddress(),
        client.getPhone(),
        client.getLogin(),
        client.getPassword());
  }
}
