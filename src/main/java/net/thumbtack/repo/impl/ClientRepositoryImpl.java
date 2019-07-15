package net.thumbtack.repo.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.thumbtack.dto.ClientListDto;
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

  @Override
  public List<ClientListDto> getAllClients() {
    List<ClientListDto> result = new ArrayList<>();

    List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM clients");
    rows.forEach(row -> {
      ClientListDto clientListDto = new ClientListDto();
      clientListDto.setId((long)row.get("id"));
      clientListDto.setFirstName((String)row.get("firstName"));
      clientListDto.setLastName((String)row.get("lastName"));
      clientListDto.setPatronymic((String)row.get("patronymic"));
      clientListDto.setEmail((String)row.get("email"));
      clientListDto.setAddress((String)row.get("postalAddress"));
      clientListDto.setPhone((String)row.get("phoneNumber"));
      clientListDto.setUserType("client");
      result.add(clientListDto);
    });
    return result;
  }
}
