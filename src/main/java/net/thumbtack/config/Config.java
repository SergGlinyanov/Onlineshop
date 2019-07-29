package net.thumbtack.config;

import net.thumbtack.repo.iface.AdminRepository;
import net.thumbtack.repo.iface.CategoryRepository;
import net.thumbtack.repo.iface.ProductRepository;
import net.thumbtack.repo.iface.ClientRepository;
import net.thumbtack.repo.iface.UtilityRepository;
import net.thumbtack.repo.impl.AdminRepositoryImpl;
import net.thumbtack.repo.impl.CategoryRepositoryImpl;
import net.thumbtack.repo.impl.ProductRepositoryImpl;
import net.thumbtack.repo.impl.ClientRepositoryImpl;
import net.thumbtack.repo.impl.UtilityRepositoryImpl;
import net.thumbtack.repo.mapper.AdminMapper;
import net.thumbtack.repo.mapper.CategoryMapper;
import net.thumbtack.repo.mapper.ProductMapper;
import net.thumbtack.repo.mapper.ClientMapper;
import net.thumbtack.repo.mapper.PurchaseProductDtoMapper;
import net.thumbtack.service.iface.AdminService;
import net.thumbtack.service.iface.CategoryService;
import net.thumbtack.service.iface.ProductService;
import net.thumbtack.service.iface.ClientService;
import net.thumbtack.service.iface.UtilityService;
import net.thumbtack.service.impl.AdminServiceImpl;
import net.thumbtack.service.impl.CategoryServiceImpl;
import net.thumbtack.service.impl.ProductServiceImpl;
import net.thumbtack.service.impl.ClientServiceImpl;
import net.thumbtack.service.impl.UtilityServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class Config {

  @Bean
  public ProductMapper productMapper() {
    return new ProductMapper();
  }

  @Bean
  public PurchaseProductDtoMapper purchaseProductDtoMapper() {
    return new PurchaseProductDtoMapper();
  }

  @Bean
  ClientMapper clientMapper() {
    return new ClientMapper();
  }

  @Bean
  AdminMapper adminMapper() {
    return new AdminMapper();
  }

  @Bean
  CategoryMapper categoryMapper() {
    return new CategoryMapper();
  }

  @Bean
  public UtilityRepository utilityRepository(JdbcTemplate jdbcTemplate){
    return new UtilityRepositoryImpl(jdbcTemplate);
  }

  @Bean
  public UtilityService utilityService(UtilityRepository utilityRepository){
    return new UtilityServiceImpl(utilityRepository);
  }

  @Bean
  public ProductRepository productRepository(JdbcTemplate jdbcTemplate,
      ProductMapper productMapper) {
    return new ProductRepositoryImpl(jdbcTemplate, productMapper);
  }

  @Bean
  public ProductService productService(ProductRepository productRepository) {
    return new ProductServiceImpl(productRepository);
  }


  @Bean
  public AdminRepository adminRepository(JdbcTemplate jdbcTemplate, AdminMapper adminMapper) {
    return new AdminRepositoryImpl(jdbcTemplate, adminMapper);
  }

  @Bean
  public AdminService adminService(AdminRepository adminRepository) {
    return new AdminServiceImpl(adminRepository);
  }

  @Bean
  public ClientRepository clientRepository(JdbcTemplate jdbcTemplate,
      ClientMapper clientMapper, PurchaseProductDtoMapper purchaseProductDto) {
    return new ClientRepositoryImpl(jdbcTemplate, clientMapper, purchaseProductDto);
  }

  @Bean
  public ClientService clientService(ClientRepository clientRepository) {
    return new ClientServiceImpl(clientRepository);
  }

  @Bean
  public CategoryRepository categoryRepository(JdbcTemplate jdbcTemplate, CategoryMapper categoryMapper) {
    return new CategoryRepositoryImpl(jdbcTemplate, categoryMapper);
  }

  @Bean
  public CategoryService categoryService(CategoryRepository categoryRepository) {
    return new CategoryServiceImpl(categoryRepository);
  }
}
