package net.thumbtack.service.iface;

import java.util.List;
import net.thumbtack.model.Product;

public interface ProductService {

  List<Product> getAllProducts();
  Product getProductById(long id);
  Product addProduct(Product product);
  void deleteProduct(Product product);
  void editProduct (Product product, long id);

}
