package net.thumbtack.repo.impl;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
      product.setNameProduct((String) row.get("nameProduct"));
      product.setPrice(((int) row.get("price")));
      product.setCount(((int) row.get("count")));
      String SQL = "SELECT id_category FROM products WHERE id = " + product.getId();
      List<Long> categories = jdbcTemplate.query(SQL, new CategoryIdMapper());
      product.setCategories(categories);
      result.add(product);
    }

    return result;
    }

  @Override
  public Product getProductById(long id) {
    String sql = "SELECT * FROM products WHERE id = ?";
    return jdbcTemplate.queryForObject(sql, new Object[]{id}, productMapper);
  }

  @Override
  public Long addProduct(Product product) {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    String sql = "INSERT INTO products (nameProduct,count,price,id_category) values (?,?,?,?)";
    jdbcTemplate.update(
        con -> {
          PreparedStatement pst =
              con.prepareStatement(sql,new String[]{"id"});
          pst.setString(1, product.getNameProduct());
          pst.setInt(2, product.getCount());
          pst.setInt(3, product.getPrice());
          pst.setLong(4, product.getCategories().get(0));
//              con.createArrayOf("bigint",
//                  new long[][]{product.getCategories().stream().mapToLong(i -> i).toArray()}));
          return pst;
        },
        keyHolder);
    return (Long)keyHolder.getKey();
  }

  @Override
  public void deleteProduct(Product product) {
    jdbcTemplate.update("DELETE FROM products WHERE id=" + product.getId());
  }

  @Override
  public void editProduct(Product product, long id) {
    jdbcTemplate.update("UPDATE products SET nameProduct = ?,"
            + " count = ?, price = ?, id_category =? WHERE id = ?",
        product.getNameProduct(),
        product.getCount(),
        product.getPrice(),
        product.getCategories().get(0),
        id);
  }
}
