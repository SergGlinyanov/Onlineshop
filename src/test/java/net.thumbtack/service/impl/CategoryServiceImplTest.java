package net.thumbtack.service.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import net.thumbtack.dto.CategoryResponseDto;
import net.thumbtack.model.Category;
import net.thumbtack.repo.iface.CategoryRepository;
import net.thumbtack.service.iface.CategoryService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

  @Autowired
  private CategoryService categoryService;

  @MockBean
  private CategoryRepository categoryRepository;


  @Test
  public void testGetCategoryById() {
    Category category = new Category( (long) 1, "Trausers", 1);
    when(categoryRepository.getCategoryById((long) 1)).thenReturn
        (category);
    Category categoryResponse = categoryService.getCategoryById(1);
    assertEquals(category.getName(), categoryResponse.getName());
  }

  @Test
  public void testDeleteCategory() {
    Category category = new Category( 1, "Trausers", 1);
    categoryRepository.deleteCategory(category);
    assertNull(categoryService.getCategoryById(1));
  }

  @Test
  public void testAddCategory() {
    Category category = new Category( 1, "Trausers", 1);
    when(categoryRepository.addCategory(category)).thenReturn
        ((long)1);
    CategoryResponseDto categoryResponseDto = categoryService.addCategory(category);
    assertEquals(category.getName(), categoryResponseDto.getName());
  }

  @Test
  public void testGetAllCategory() {
    List<Category> categories = Arrays.asList(
        new Category( 1, "Trausers", 1),
        new Category( 2, "Trausers", 1)
    );
    when(categoryRepository.getAllCategory()).thenReturn
        (categories);
    List<Category> response = categoryService.getAllCategory();
    assertEquals(categories.size(), response.size());
  }

  @Test
  public void testGetNameParentCategory() {
    when(categoryRepository.getNameParentCategory(1)).thenReturn
        ("Trausers");
    String response = categoryService.getNameParentCategory(1);
    assertEquals("Trausers", response);
  }

  @Test
  public void testEditCategory() {
    Category category = new Category( 1, "Trausers", 1);
    categoryRepository.editCategory(category, 1);
  }
}