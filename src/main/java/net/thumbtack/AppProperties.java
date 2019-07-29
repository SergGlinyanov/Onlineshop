package net.thumbtack;

import javax.validation.constraints.Max;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "onlineshop")
@Validated
public class AppProperties {
  private int rest_http_port;

  @Max(value = 50, message = "Value 'name' should not be greater than 50")
  private int max_name_length;

  @Max(value = 8, message = "Value 'password' should not be greater than 8")
  private int min_password_length;

  public int getRest_http_port() {
    return rest_http_port;
  }

  public void setRest_http_port(int rest_http_port) {
    this.rest_http_port = rest_http_port;
  }

  public int getMax_name_length() {
    return max_name_length;
  }

  public void setMax_name_length(int max_name_length) {
    this.max_name_length = max_name_length;
  }

  public int getMin_password_length() {
    return min_password_length;
  }

  public void setMin_password_length(int min_password_length) {
    this.min_password_length = min_password_length;
  }
}
