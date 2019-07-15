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
  public Product getProductById(long id) {
    return productRepository.getProductById(id);
  }

  @Override
  public Product addProduct(Product product) {
    return new Product(productRepository.addProduct(product),
        product.getNameProduct(), product.getPrice(), product.getCount(), product.getCategories());
  }

  @Override
  public void deleteProduct(Product product) {
    productRepository.deleteProduct(product);
  }

  @Override
  public void editProduct(Product product, long id) {
    productRepository.editProduct(product, id);
  }
}
