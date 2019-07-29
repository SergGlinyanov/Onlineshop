package net.thumbtack.repo.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import net.thumbtack.dto.ClientPurchasesDto;
import org.springframework.jdbc.core.RowMapper;

public class ClientPurchasesMapper implements RowMapper<ClientPurchasesDto> {

  @Override
  public ClientPurchasesDto mapRow(ResultSet resultSet, int i) throws SQLException {
    ClientPurchasesDto client = new ClientPurchasesDto();
    client.setLastName(resultSet.getString("lastName"));
    client.setFirstName(resultSet.getString("firstName"));
    client.setPatronymic(resultSet.getString("patronymic"));
    client.setEmail(resultSet.getString("email"));
    client.setAddress(resultSet.getString("postalAddress"));
    client.setPhone(resultSet.getString("phoneNumber"));
    return client;
  }

}
