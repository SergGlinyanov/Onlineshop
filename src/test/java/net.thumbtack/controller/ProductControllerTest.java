package net.thumbtack.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import net.thumbtack.model.Product;
import net.thumbtack.service.iface.ProductService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

public class ProductControllerTest {

  private ProductController underTest;

  @Mock
  private ProductService productService;

  @Captor
  private ArgumentCaptor<Product> captor;

  @Before
  public void setUpClass() {
    MockitoAnnotations.initMocks(this);
    underTest = new ProductController(this.productService);
  }

  @Test
  public void testAddProduct() {
    Product product = new Product( 1, "Trausers", 100);
    underTest.addProduct(product);
    verify(productService).addProduct(captor.capture());
    Product value = captor.getValue();
    assertEquals(product.getId(), value.getId());
  }


  @Test
  public void testGetAllProductAndDeleteProduct() {
    List<Product> products = Arrays.asList(
        new Product( 1, "Trausers", 1),
        new Product( 2, "Trausers", 1),
        new Product( 2, "Trausers", 1),
        new Product( 2, "Trausers", 1)
    );
    when(productService.getAllProducts()).thenReturn(products);
    List<Product> productList = underTest.getAllProducts().getBody();
    assertEquals(4, productList.size());

    assertEquals(HttpStatus.OK, underTest.deleteProduct(1).getStatusCode());
  }

  @Ignore
  @Test
  public void testGetProductById() {
    Product product = new Product( 1, "Trausers", 1);
    underTest.addProduct(product);
    verify(productService).getProductById(1);
    Product value = captor.getValue();
    assertEquals(product.getId(), value.getId());
  }



}
