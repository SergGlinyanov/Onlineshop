package net.thumbtack.service.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import net.thumbtack.model.Category;
import net.thumbtack.repo.iface.CategoryRepository;
import net.thumbtack.service.iface.CategoryService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CategoriesServiceImplTest {

  private CategoryService underTest;

  @Captor
  private ArgumentCaptor<Category> captor;

  @Mock
  private CategoryRepository categoryRepository;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    underTest = new CategoryServiceImpl(categoryRepository);
  }

  @Test
  public void testAddCategory() {
    Category category = new Category( 1, "Trausers", 1);
    underTest.addCategory(category);
    verify(categoryRepository).addCategory(captor.capture());
    Category value = captor.getValue();
    assertEquals(category.getId(), value.getId());
  }

  @Ignore
  @Test
  public void testGetAllCategoryAndDeleteCategory() {
    List<Category> categories = Arrays.asList(
        new Category( 1, "Trausers", 1),
        new Category( 2, "Trausers", 1)
    );
    when(categoryRepository.getAllCategory()).thenReturn(categories);
    List<Category> categoryList = underTest.getAllCategory();
    assertThat(categoryList, hasSize(categories.size()));

    underTest.deleteCategory(categoryList.get(0));
    List<Category> categoryList1 = underTest.getAllCategory();
    assertThat(categoryList1, hasSize(categories.size()-1));
  }

  @Ignore
  @Test
  public void testGetCategoryById() {
    Category category = new Category( 1, "Trausers", 1);
    underTest.getCategoryById(1);
    verify(categoryRepository).getCategoryById(1);
    Category value = captor.getValue();
    assertEquals(category.getId(), value.getId());
  }
}
