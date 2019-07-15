package net.thumbtack.controller;

import java.util.List;
import net.thumbtack.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

  private final net.thumbtack.service.iface.ProductService productService;

  public ProductController(net.thumbtack.service.iface.ProductService productService) {
    this.productService = productService;
  }



  @PostMapping(value = "api/products")
  public ResponseEntity<Product> addProduct(@RequestBody Product product) {
    return new ResponseEntity<>(productService.addProduct(product), HttpStatus.OK);
  }

  @DeleteMapping("api/products/{id}")
  public ResponseEntity<Product> deleteProduct(@PathVariable long id) {
    Product product = productService.getProductById(id);
    this.productService.deleteProduct(product);
    return new ResponseEntity<>(product, HttpStatus.OK);
  }

  @GetMapping("api/products/{id}")
  public ResponseEntity<Product> getProductById(@PathVariable int id) {
    Product product = productService.getProductById(id);
    return new ResponseEntity<>(product, HttpStatus.OK);
  }

  @GetMapping("api/products")
  public ResponseEntity<List<Product>> getAllProducts() {
    List<Product> allProduct = this.productService.getAllProducts();
    return new ResponseEntity<>(allProduct, HttpStatus.OK);
  }

  @PutMapping("api/products/{id}")
  public ResponseEntity<Product> editProduct
      (@RequestBody Product product, @PathVariable long id){
    productService.editProduct(product, id);
    return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
  }

}
