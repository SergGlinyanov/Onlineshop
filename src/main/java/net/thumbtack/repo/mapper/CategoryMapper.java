package net.thumbtack.repo.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import net.thumbtack.model.Category;
import org.springframework.jdbc.core.RowMapper;

public class CategoryMapper implements RowMapper<Category> {

  @Override
  public Category mapRow(ResultSet resultSet, int i) throws SQLException {
    Category category = new Category();
    category.setId(resultSet.getLong("id"));
    category.setName(resultSet.getString("nameCategory"));
    category.setParentId(resultSet.getLong("parentId"));
    return category;
  }
}
