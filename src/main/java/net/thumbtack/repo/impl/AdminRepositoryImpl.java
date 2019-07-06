package net.thumbtack.repo.impl;

import net.thumbtack.model.Category;
import net.thumbtack.model.Product;
import net.thumbtack.repo.iface.AdminRepository;
import net.thumbtack.repo.mapper.ProductMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class AdminRepositoryImpl implements AdminRepository {

  private JdbcTemplate jdbcTemplate;
  private ProductMapper productMapper;

  public AdminRepositoryImpl(JdbcTemplate jdbcTemplate, ProductMapper productMapper) {
    this.jdbcTemplate = jdbcTemplate;
    this.productMapper = productMapper;
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

  }

  @Override
  public void deleteCategory(Category category) {

  }
}
