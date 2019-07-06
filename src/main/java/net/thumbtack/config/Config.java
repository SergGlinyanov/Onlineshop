package net.thumbtack.config;

import net.thumbtack.repo.iface.ProductRepository;
import net.thumbtack.repo.impl.ProductRepositoryImpl;
import net.thumbtack.repo.mapper.ProductMapper;
import net.thumbtack.service.iface.ProductService;
import net.thumbtack.service.impl.ProductSeviceImpl;
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
    public ProductRepository productRepository(JdbcTemplate jdbcTemplate, ProductMapper productMapper) {
      return new ProductRepositoryImpl(jdbcTemplate, productMapper);
    }

    @Bean
    public ProductService productService(ProductRepository productRepository) {
      return new ProductSeviceImpl(productRepository);
    }

}
