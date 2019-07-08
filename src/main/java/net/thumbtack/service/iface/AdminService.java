package net.thumbtack.service.iface;

import net.thumbtack.model.Category;
import net.thumbtack.model.Product;

public interface AdminService {
  void addProduct(Product product);
  void deleteProduct(Product product);
  void addCategory(Category category);
  void deleteCategory(Category category);
  Product getProductById(int id);
  Category getCategoryById(int id);

}
