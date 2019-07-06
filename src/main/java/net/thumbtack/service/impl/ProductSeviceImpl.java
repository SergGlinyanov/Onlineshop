package net.thumbtack.service.impl;

import java.util.List;
import net.thumbtack.model.Product;
import net.thumbtack.repo.iface.ProductRepository;
import net.thumbtack.service.iface.ProductService;

public class ProductSeviceImpl implements ProductService {

  private final ProductRepository productRepository;

  public ProductSeviceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public List<Product> getAllProducts() {
    return productRepository.getAllProducts();
  }

  @Override
  public void addProduct(Product product) {
    productRepository.saveProduct(product);
  }

  @Override
  public Product getProductById(int id) {
    return productRepository.getProductById(id);
  }
}
