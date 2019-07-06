package net.thumbtack.repo.iface;

import java.util.List;
import net.thumbtack.model.Product;

public interface ProductRepository {

  List<Product> getAllProducts();
  void saveProduct(Product product);
  Product getProductById(int id);
}
