package net.thumbtack.service.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import net.thumbtack.model.Product;
import net.thumbtack.repo.iface.ProductRepository;
import net.thumbtack.service.iface.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

  @Autowired
  private ProductService productService;

  @MockBean
  private ProductRepository productRepository;

  @Captor
  public ArgumentCaptor<Product> captor;

  @Test
  public void testGetAllProducts() {
    List<Product> products = Arrays.asList(
        new Product( 1, "Trausers", 1),
        new Product( 2, "Trausers", 1)
    );
    when(productRepository.getAllProducts()).thenReturn(products);
    List<Product> productList = productService.getAllProducts();
    assertEquals(productList.size(), products.size());
  }

  @Test
  public void testGetProductById() {
    Product product = new Product( 1, "Trausers", 100);
    when(productRepository.getProductById(1)).thenReturn(product);
    Product response = productService.getProductById(1);
    assertEquals(product.getName(), response.getName());
  }

  @Test
  public void testAddProduct() {
    Product product = new Product( 1, "Trausers", 100);
    when(productRepository.addProduct(product)).thenReturn((long)1);
    Product response = productService.addProduct(product);
    assertEquals(product.getName(), response.getName());
  }

  @Test
  public void testDeleteProduct() {
    productService.deleteProduct(1);
  }

  @Test
  public void testEditProduct() {
    Product product = new Product( 1, "Trausers", 100);
    productService.editProduct(product, 1);
  }
}