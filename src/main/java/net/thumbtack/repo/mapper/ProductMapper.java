package net.thumbtack.repo.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import net.thumbtack.model.Product;
import org.springframework.jdbc.core.RowMapper;

public class ProductMapper implements RowMapper<Product> {

  @Override
  public Product mapRow(ResultSet resultSet, int i) throws SQLException {
    Product product = new Product();
    product.setId(resultSet.getLong("id"));
    product.setName(resultSet.getString("name"));
    product.setPrice(resultSet.getInt("price"));
    product.setCount(resultSet.getInt("count"));
    return product;
  }
}
