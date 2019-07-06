package net.thumbtack.repo.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.thumbtack.model.Product;
import net.thumbtack.repo.iface.ProductRepository;
import net.thumbtack.repo.mapper.ProductMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class ProductRepositoryImpl implements ProductRepository {

  private JdbcTemplate jdbcTemplate;
  private ProductMapper productMapper;

  public ProductRepositoryImpl(JdbcTemplate jdbcTemplate, ProductMapper productMapper) {
    this.jdbcTemplate = jdbcTemplate;
    this.productMapper = productMapper;
  }

  @Override
  public List<Product> getAllProducts() {
    List<Product> result = new ArrayList<>();

    List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM products");
    rows.forEach(row -> {
      Product product = new Product();
      product.setId((long)row.get("id"));
      product.setNameProduct((String)row.get("nameProduct"));
      product.setPrice(((int) row.get("price")));
      result.add(product);
});

    return result;
    }

  @Override
  public void saveProduct(Product product) {
    jdbcTemplate.update("INSERT INTO products (nameProduct, price) values (?,?)",
        product.getNameProduct(),
        product.getPrice());
  }

  @Override
  public Product getProductById(int id) {
    String sql = "SELECT * FROM products WHERE id = ?";
    return jdbcTemplate.queryForObject(sql, new Object[]{id}, productMapper);
  }
}
