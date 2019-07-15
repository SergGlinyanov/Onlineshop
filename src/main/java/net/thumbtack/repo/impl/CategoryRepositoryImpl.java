package net.thumbtack.repo.impl;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.thumbtack.model.Category;
import net.thumbtack.repo.iface.CategoryRepository;
import net.thumbtack.repo.mapper.CategoryMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class CategoryRepositoryImpl implements CategoryRepository {

  private JdbcTemplate jdbcTemplate;
  private CategoryMapper categoryMapper;

  public CategoryRepositoryImpl(JdbcTemplate jdbcTemplate, CategoryMapper categoryMapper) {
    this.jdbcTemplate = jdbcTemplate;
    this.categoryMapper = categoryMapper;
  }

  @Override
  public Category getCategoryById(long id) {
    String sql = "SELECT * FROM categories WHERE id = ?";
    return jdbcTemplate.queryForObject(sql, new Object[]{id}, categoryMapper);
  }

  @Override
  public void deleteCategory(Category category) {
    jdbcTemplate.update("DELETE FROM categories WHERE id=" + category.getId());
  }

  @Override
  public Long addCategory(Category category) {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    String sql = "INSERT INTO categories (nameCategory, id_parent_category) values (?,?)";
    jdbcTemplate.update(
        con -> {
          PreparedStatement pst =
              con.prepareStatement(sql,new String[]{"id"});
          pst.setString(1, category.getName());
          pst.setLong(2, category.getIdParentCategory());
          return pst;
        },
        keyHolder);
    return (Long)keyHolder.getKey();
  }

  @Override
  public List<Category> getAllCategory() {
    List<Category> result = new ArrayList<>();

    List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM categories");
    rows.forEach(row -> {
      Category category = new Category();
      category.setId((long)row.get("id"));
      category.setName((String)row.get("nameCategory"));
      category.setIdParentCategory(((long) row.get("id_parent_category")));
      result.add(category);
    });
    return result;
  }

  @Override
  public String getNameParentCategory(long id) {
    String sql = "SELECT nameCategory FROM categories WHERE id = " + id;
    return jdbcTemplate.queryForObject(sql, String.class);
  }

  @Override
  public void editCategory(Category category, long id) {
    jdbcTemplate.update("UPDATE categories SET nameCategory = ?,"
            + " id_parent_category = ? WHERE id = ?",
        category.getName(),
        category.getIdParentCategory(),
        id);
  }


}
