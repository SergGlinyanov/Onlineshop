package net.thumbtack.controller;

import net.thumbtack.model.Category;
import net.thumbtack.model.Product;
import net.thumbtack.service.iface.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
  private final net.thumbtack.service.iface.AdminService adminService;

  public AdminController(AdminService adminService) {
    this.adminService = adminService;
  }

  @PostMapping(value = "product/add")
  public void addProduct(@RequestBody Product product) {
    this.adminService.addProduct(product);
  }

  @DeleteMapping("product/delete/{id}")
  public ResponseEntity<Product> deleteProduct(@PathVariable int id) {
    Product product = adminService.getProductById(id);
    this.adminService.deleteProduct(product);
    return new ResponseEntity<>(product, HttpStatus.OK);
  }

  @PostMapping(value = "category/add")
  public void addCategory(@RequestBody Category category) {
    this.adminService.addCategory(category);
  }

  @DeleteMapping("category/delete/{id}")
  public ResponseEntity<Category> deleteCategory(@PathVariable int id) {
    Category category = adminService.getCategoryById(id);
    this.adminService.deleteCategory(category);
    return new ResponseEntity<>(category, HttpStatus.OK);
  }
}
