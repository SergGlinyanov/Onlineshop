package net.thumbtack.repo.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import net.thumbtack.dto.ProductPurchasesDto;
import org.springframework.jdbc.core.RowMapper;

public class ProductPurchasesMapper implements RowMapper<ProductPurchasesDto> {

  @Override
  public ProductPurchasesDto mapRow(ResultSet resultSet, int i) throws SQLException {
    ProductPurchasesDto product = new ProductPurchasesDto();
    product.setName(resultSet.getString("name"));
    product.setPrice(resultSet.getInt("price"));
    product.setCount(resultSet.getInt("count"));
    return product;
  }

}
