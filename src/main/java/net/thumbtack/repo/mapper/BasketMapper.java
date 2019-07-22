package net.thumbtack.repo.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class BasketMapper implements RowMapper<Long> {

  @Override
  public Long mapRow(ResultSet resultSet, int i) throws SQLException {
    return resultSet.getLong("id_product");
  }

}
