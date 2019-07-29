package net.thumbtack.service.impl;

import java.util.List;
import net.thumbtack.dto.CategoryResponseDto;
import net.thumbtack.model.Category;
import net.thumbtack.repo.iface.CategoryRepository;
import net.thumbtack.service.iface.CategoryService;

public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;

  public CategoryServiceImpl(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  @Override
  public Category getCategoryById(long id) {
    return categoryRepository.getCategoryById(id);
  }

  @Override
  public void deleteCategory(Category category) {
    categoryRepository.deleteCategory(category);
  }

  @Override
  public CategoryResponseDto addCategory(Category category) {
    return new CategoryResponseDto(categoryRepository.addCategory(category),
        category.getName(), category.getParentId(),
        categoryRepository.getNameParentCategory(category.getParentId()));
  }

  @Override
  public List<Category> getAllCategory() {
    return categoryRepository.getAllCategory();
  }

  @Override
  public String getNameParentCategory(long id) {
    return categoryRepository.getNameParentCategory(id);
  }

  @Override
  public void editCategory(Category category, long id) {
    categoryRepository.editCategory(category, id);
  }
}
