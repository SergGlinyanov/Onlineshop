package net.thumbtack.repo.impl;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import net.thumbtack.model.Product;
import net.thumbtack.repo.iface.ProductRepository;
import net.thumbtack.repo.mapper.CategoryIdMapper;
import net.thumbtack.repo.mapper.ProductMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

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
    for (Map<String, Object> row : rows) {

      Product product = new Product();
      product.setId((long) row.get("id"));
      product.setName((String) row.get("name"));
      product.setPrice(((int) row.get("price")));
      product.setCount(((int) row.get("count")));
      String SQL = "SELECT id_category FROM products_categories WHERE id_product = "
          + product.getId();
      List<Long> categories = jdbcTemplate.query(SQL, new CategoryIdMapper());
      product.setCategories(categories);
      result.add(product);
    }

    return result;
    }

  @Override
  public Product getProductById(long id) {
    String sql = "SELECT * FROM products WHERE id = ?";
    Product product = jdbcTemplate.queryForObject(sql, new Object[]{id}, productMapper);
    String SQL = "SELECT id_category FROM products_categories WHERE id_product = "
        + product.getId();
    List<Long> categories = jdbcTemplate.query(SQL, new CategoryIdMapper());
    product.setCategories(categories);
    return product;
  }

  @Override
  public Long addProduct(Product product) {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    String sql = "INSERT INTO products (name,count,price) values (?,?,?)";
    jdbcTemplate.update(
        con -> {
          PreparedStatement pst =
              con.prepareStatement(sql,new String[]{"id"});
          pst.setString(1, product.getName());
          pst.setInt(2, product.getCount());
          pst.setInt(3, product.getPrice());
          return pst;
        },
        keyHolder);
    long id_product = (Long)keyHolder.getKey();
    List<Long> categories = product.getCategories();
    List<Long> deduped = categories.stream().distinct().collect(Collectors.toList());
    for (Long id_category : deduped) {
      jdbcTemplate.update("INSERT INTO products_categories (id_product,id_category)"
              + " values (?,?)",
          id_product,
          id_category
      );
    }
    return id_product;
  }

  @Override
  public void deleteProduct(long id) {
    jdbcTemplate.update("DELETE FROM products WHERE id=" + id);
  }

  @Override
  public void editProduct(Product product, long id) {
    jdbcTemplate.update("UPDATE products SET name = ?,"
            + " count = ?, price = ? WHERE id = ?",
        product.getName(),
        product.getCount(),
        product.getPrice(),
        id);
    jdbcTemplate.update("DELETE FROM products_categories WHERE id_product=" + id);
    List<Long> categories = product.getCategories();
    List<Long> deduped = categories.stream().distinct().collect(Collectors.toList());
    for (Long id_category : deduped) {
        jdbcTemplate.update("INSERT INTO products_categories (id_product,id_category)"
                + " values (?,?)",
            id,
            id_category
        );
    }
  }
}
