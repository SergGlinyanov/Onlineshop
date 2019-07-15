package net.thumbtack.repo.iface;

import java.util.List;
import net.thumbtack.model.Product;

public interface ProductRepository {

  List<Product> getAllProducts();
  Product getProductById(long id);
  Long addProduct(Product product);
  void deleteProduct(Product product);
  void editProduct (Product product, long id);
}
