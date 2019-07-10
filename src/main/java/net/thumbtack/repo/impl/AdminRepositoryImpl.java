package net.thumbtack.repo.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.thumbtack.model.Admin;
import net.thumbtack.model.Category;
import net.thumbtack.model.Product;
import net.thumbtack.repo.iface.AdminRepository;
import net.thumbtack.repo.mapper.AdminMapper;
import net.thumbtack.repo.mapper.CategoryMapper;
import net.thumbtack.repo.mapper.ProductMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class AdminRepositoryImpl implements AdminRepository {

  private JdbcTemplate jdbcTemplate;
  private ProductMapper productMapper;
  private CategoryMapper categoryMapper;
  private AdminMapper adminMapper;

  public AdminRepositoryImpl(JdbcTemplate jdbcTemplate, ProductMapper productMapper) {
    this.jdbcTemplate = jdbcTemplate;
    this.productMapper = productMapper;
  }

  public AdminRepositoryImpl(JdbcTemplate jdbcTemplate, CategoryMapper categoryMapper) {
    this.jdbcTemplate = jdbcTemplate;
    this.categoryMapper = categoryMapper;
  }

  public AdminRepositoryImpl(JdbcTemplate jdbcTemplate, AdminMapper adminMapper) {
    this.jdbcTemplate = jdbcTemplate;
    this.adminMapper = adminMapper;
  }

  @Override
  public void addAdmin(Admin admin) {
    jdbcTemplate.update("INSERT INTO admins (lastName,firstName,patronymic,position,"
            + "login,password) values (?,?,?,?,?,?)",
        admin.getLastName(),
        admin.getFirstName(),
        admin.getPatronymic(),
        admin.getPosition(),
        admin.getLogin(),
        admin.getPassword());
  }

  @Override
  public void addProduct(Product product) {
    jdbcTemplate.update("INSERT INTO products (nameProduct,count,price,id_category) values (?,?,?,?)",
    product.getNameProduct(),
        product.getCount(),
        product.getPrice(),
        product.getCategories());
  }

  @Override
  public void deleteProduct(Product product) {
    jdbcTemplate.update("DELETE FROM products WHERE id=" + product.getId());
  }

  @Override
  public void addCategory(Category category) {
    jdbcTemplate.update("INSERT INTO categories (nameCategory, id_parent_category) values (?,?)",
        category.getName(),
        category.getIdParentCategory());
  }

  @Override
  public void deleteCategory(Category category) {
    jdbcTemplate.update("DELETE FROM categories WHERE id=" + category.getId());
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
  public Product getProductById(int id) {
    String sql = "SELECT * FROM products WHERE id = ?";
    return jdbcTemplate.queryForObject(sql, new Object[]{id}, productMapper);
  }

  @Override
  public Category getCategoryById(int id) {
    String sql = "SELECT * FROM categories WHERE id = ?";
    return jdbcTemplate.queryForObject(sql, new Object[]{id}, categoryMapper);
  }


}
