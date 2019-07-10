package net.thumbtack.service.iface;

import java.util.List;
import net.thumbtack.model.Admin;
import net.thumbtack.model.Category;
import net.thumbtack.model.Product;

public interface AdminService {

  void addAdmin(Admin admin);
  void addProduct(Product product);
  void deleteProduct(Product product);
  void addCategory(Category category);
  void deleteCategory(Category category);
  List<Category> getAllCategory();
  Product getProductById(int id);
  Category getCategoryById(int id);

}
