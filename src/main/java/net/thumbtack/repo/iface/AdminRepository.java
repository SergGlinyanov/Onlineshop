package net.thumbtack.repo.iface;

import net.thumbtack.model.Category;
import net.thumbtack.model.Product;

public interface AdminRepository {

  void addProduct(Product product);

  void deleteProduct(Product product);

  void addCategory(Category category);

  void deleteCategory(Category category);

}
