package net.thumbtack.repo.iface;

import java.util.List;
import net.thumbtack.model.Category;

public interface CategoryRepository {

  Category getCategoryById(long id);
  void deleteCategory(Category category);
  Long addCategory(Category category);
  List<Category> getAllCategory();
  String getNameParentCategory(long id);
  void editCategory(Category category, long id);

}
