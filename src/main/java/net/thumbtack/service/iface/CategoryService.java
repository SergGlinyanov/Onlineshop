package net.thumbtack.service.iface;

import java.util.List;
import net.thumbtack.dto.ResponseCategoryDto;
import net.thumbtack.model.Category;

public interface CategoryService {

  Category getCategoryById(long id);
  void deleteCategory(Category category);
  ResponseCategoryDto addCategory(Category category);
  List<Category> getAllCategory();
  String getNameParentCategory(long id);
  void editCategory(Category category, long id);

}
