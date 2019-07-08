package net.thumbtack.service.impl;

import net.thumbtack.model.Category;
import net.thumbtack.model.Product;
import net.thumbtack.repo.iface.AdminRepository;
import net.thumbtack.service.iface.AdminService;

public class AdminServiceImpl implements AdminService {

  private final AdminRepository adminRepository;

  public AdminServiceImpl(AdminRepository adminRepository) {
    this.adminRepository = adminRepository;
  }

  @Override
  public void addProduct(Product product) {
    adminRepository.addProduct(product);
  }

  @Override
  public void deleteProduct(Product product) {
    adminRepository.deleteProduct(product);
  }

  @Override
  public void addCategory(Category category) {
    adminRepository.addCategory(category);
  }

  @Override
  public void deleteCategory(Category category) {
    adminRepository.deleteCategory(category);
  }

  @Override
  public Product getProductById(int id) {
    return adminRepository.getProductById(id);
  }

  @Override
  public Category getCategoryById(int id) {
    return adminRepository.getCategoryById(id);
  }
}
