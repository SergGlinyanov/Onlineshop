package net.thumbtack.service.impl;

import java.util.List;
import net.thumbtack.model.Admin;
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
  public void addAdmin(Admin admin) {
    adminRepository.addAdmin(admin);
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
  public List<Category> getAllCategory() {
    return adminRepository.getAllCategory();
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
