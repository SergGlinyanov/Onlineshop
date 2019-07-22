package net.thumbtack.controller;

import java.util.ArrayList;
import java.util.List;
import net.thumbtack.dto.CategoryResponseDto;
import net.thumbtack.model.Category;
import net.thumbtack.service.iface.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {

  private final net.thumbtack.service.iface.CategoryService categoryService;

  public CategoriesController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<CategoryResponseDto> getCategoryById(@PathVariable long id) {
    Category category = categoryService.getCategoryById(id);
    CategoryResponseDto categoryResponseDto = new CategoryResponseDto(category.getId(),
        category.getName(), category.getIdParentCategory(),
        categoryService.getNameParentCategory(category.getIdParentCategory()));
    return new ResponseEntity<>(categoryResponseDto, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public void deleteCategory(@PathVariable long id) {
    Category category = categoryService.getCategoryById(id);
    categoryService.deleteCategory(category);
  }

  @PostMapping
  public ResponseEntity<CategoryResponseDto> addCategory(@RequestBody Category category) {
    return new ResponseEntity<>(categoryService.addCategory(category), HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<CategoryResponseDto>> getAllCategories() {
    List<Category> categories = this.categoryService.getAllCategory();
    List<CategoryResponseDto> categoryResponseDtoList = new ArrayList<>();
    for (Category category: categories){
      categoryResponseDtoList.add(new CategoryResponseDto(category.getId(),
          category.getName(), category.getIdParentCategory(),
          categoryService.getNameParentCategory(category.getIdParentCategory())));
    }
    return new ResponseEntity<>(categoryResponseDtoList, HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<CategoryResponseDto> editCategory
      (@RequestBody Category category, @PathVariable long id){
    categoryService.editCategory(category, id);
    CategoryResponseDto categoryResponseDto = new CategoryResponseDto(id,
        category.getName(), category.getIdParentCategory(),
        categoryService.getNameParentCategory(category.getIdParentCategory()));
    return new ResponseEntity<>(categoryResponseDto, HttpStatus.OK);
  }
}
