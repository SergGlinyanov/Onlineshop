package net.thumbtack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class OnlineShopServer {
  public static void main(String[] args) {
    SpringApplication.run(OnlineShopServer.class, args);
  }
}
