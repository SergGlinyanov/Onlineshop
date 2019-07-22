package net.thumbtack.controller;

import java.util.List;
import net.thumbtack.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  private final net.thumbtack.service.iface.ProductService productService;

  public ProductController(net.thumbtack.service.iface.ProductService productService) {
    this.productService = productService;
  }



  @PostMapping
  public ResponseEntity<Product> addProduct(@RequestBody Product product) {
    return new ResponseEntity<>(productService.addProduct(product), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteProduct(@PathVariable long id) {
    productService.deleteProduct(id);
    return  new ResponseEntity<>("{}",HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> getProductById(@PathVariable int id) {
    return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<Product>> getAllProducts() {
    List<Product> allProduct = this.productService.getAllProducts();
    return new ResponseEntity<>(allProduct, HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Product> editProduct
      (@RequestBody Product product, @PathVariable long id){
    productService.editProduct(product, id);
    return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
  }

}
