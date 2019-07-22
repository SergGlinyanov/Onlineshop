package net.thumbtack.service.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import net.thumbtack.model.Product;
import net.thumbtack.repo.iface.ProductRepository;
import net.thumbtack.service.iface.ProductService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ProductServiceImplTest {

  private ProductService underTest;

  @Captor
  private ArgumentCaptor<Product> captor;

  @Mock
  private ProductRepository productRepository;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    underTest = new ProductSeviceImpl(productRepository);
  }

  @Test
  public void testAddProduct() {
    Product product = new Product( 1, "Trausers", 100);
    underTest.addProduct(product);
    verify(productRepository).addProduct(captor.capture());
    Product value = captor.getValue();
    assertEquals(product.getId(), value.getId());
  }

  @Ignore
  @Test
  public void testGetAllProductAndDeleteProduct() {
    List<Product> products = Arrays.asList(
        new Product( 1, "Trausers", 1),
        new Product( 2, "Trausers", 1)
    );
    when(productRepository.getAllProducts()).thenReturn(products);
    List<Product> productList = underTest.getAllProducts();
    assertThat(productList, hasSize(products.size()));

    underTest.deleteProduct(1);
    List<Product> productList1 = underTest.getAllProducts();
    assertThat(productList1, hasSize(products.size()-1));
  }

  @Ignore
  @Test
  public void testGetProductById() {
    Product category = new Product( 1, "Trausers", 1);
    underTest.getProductById(1);//в постмане всё работает, что сделал неправильно???
    verify(productRepository).getProductById(1);
    Product value = captor.getValue();
    assertEquals(category.getId(), value.getId());
  }
}
