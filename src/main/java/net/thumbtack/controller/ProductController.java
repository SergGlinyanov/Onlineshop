package net.thumbtack.controller;

import java.util.List;
import net.thumbtack.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

  private final net.thumbtack.service.iface.ProductService productService;

  public ProductController(net.thumbtack.service.iface.ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("products")
  public ResponseEntity<List<Product>> getAllProducts() {
    List<Product> allProduct = this.productService.getAllProducts();

    return new ResponseEntity<>(allProduct, HttpStatus.OK);
  }

  @GetMapping("product/{id}")
  public ResponseEntity<Product> getProductById(@PathVariable int id) {
    Product product = productService.getProductById(id);
    return new ResponseEntity<>(product, HttpStatus.OK);
  }

  @PostMapping("addProduct")
  public void addProduct(@RequestBody Product product) {
    this.productService.addProduct(product);
  }
}
