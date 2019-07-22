package net.thumbtack.repo.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import net.thumbtack.dto.ProductDto;
import org.springframework.jdbc.core.RowMapper;

public class PurchaseProductDtoMapper implements RowMapper<ProductDto> {

  @Override
  public ProductDto mapRow(ResultSet resultSet, int i) throws SQLException {
    ProductDto product = new ProductDto();
    product.setId(resultSet.getLong("id"));
    product.setName(resultSet.getString("name"));
    product.setPrice(resultSet.getInt("price"));
    product.setCount(resultSet.getInt("count"));
    return product;
  }

}
