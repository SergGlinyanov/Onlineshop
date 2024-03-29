package net.thumbtack.service.impl;

import java.util.List;
import net.thumbtack.model.Product;
import net.thumbtack.repo.iface.ProductRepository;
import net.thumbtack.service.iface.ProductService;

public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public List<Product> getAllProducts() {
    return productRepository.getAllProducts();
  }

  @Override
  public Product getProductById(long id) {
    return productRepository.getProductById(id);
  }

  @Override
  public Product addProduct(Product product) {
    return new Product(productRepository.addProduct(product),
        product.getName(), product.getPrice(), product.getCount(), product.getCategories());
  }

  @Override
  public void deleteProduct(long id) {
    productRepository.deleteProduct(id);
  }

  @Override
  public void editProduct(Product product, long id) {
    productRepository.editProduct(product, id);
  }
}
