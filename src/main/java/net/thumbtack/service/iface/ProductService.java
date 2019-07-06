package net.thumbtack.service.iface;

import java.util.List;
import net.thumbtack.model.Product;

public interface ProductService {

  List<Product> getAllProducts();
  void addProduct(Product product);
  Product getProductById(int id);

}
