package net.thumbtack.controller;

import java.util.ArrayList;
import java.util.List;
import net.thumbtack.dto.ResponseCategoryDto;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoriesController {

  private final net.thumbtack.service.iface.CategoryService categoryService;

  public CategoriesController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping("api/categories/{id}")
  public ResponseEntity<ResponseCategoryDto> getCategoryById(@PathVariable long id) {
    Category category = categoryService.getCategoryById(id);
    ResponseCategoryDto responseCategoryDto = new ResponseCategoryDto(category.getId(),
        category.getName(), category.getIdParentCategory(),
        categoryService.getNameParentCategory(category.getIdParentCategory()));
    return new ResponseEntity<>(responseCategoryDto, HttpStatus.OK);
  }

  @DeleteMapping("api/categories/{id}")
  public void deleteCategory(@PathVariable long id) {
    Category category = categoryService.getCategoryById(id);
    categoryService.deleteCategory(category);
  }

  @PostMapping(value = "api/categories")
  public ResponseEntity<ResponseCategoryDto> addCategory(@RequestBody Category category) {
    return new ResponseEntity<>(categoryService.addCategory(category), HttpStatus.OK);
  }

  @GetMapping("api/categories")
  public ResponseEntity<List<ResponseCategoryDto>> getAllCategories() {
    List<Category> categories = this.categoryService.getAllCategory();
    List<ResponseCategoryDto> responseCategoryDtoList = new ArrayList<>();
    for (Category category: categories){
      responseCategoryDtoList.add(new ResponseCategoryDto(category.getId(),
          category.getName(), category.getIdParentCategory(),
          categoryService.getNameParentCategory(category.getIdParentCategory())));
    }
    return new ResponseEntity<>(responseCategoryDtoList, HttpStatus.OK);
  }

  @PutMapping("api/categories/{id}")
  public ResponseEntity<ResponseCategoryDto> editCategory
      (@RequestBody Category category, @PathVariable long id){
    categoryService.editCategory(category, id);
    ResponseCategoryDto responseCategoryDto = new ResponseCategoryDto(id,
        category.getName(), category.getIdParentCategory(),
        categoryService.getNameParentCategory(category.getIdParentCategory()));
    return new ResponseEntity<>(responseCategoryDto, HttpStatus.OK);
  }
}
