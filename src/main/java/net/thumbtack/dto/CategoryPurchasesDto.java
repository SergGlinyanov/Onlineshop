package net.thumbtack.dto;

import java.util.List;

public class CategoryPurchasesDto {
  String name;
  private List<ProductDto> products;
  private int total;

  public CategoryPurchasesDto(String name,
      List<ProductDto> purchaseProducts, int total) {
    this.name = name;
    this.products = purchaseProducts;
    this.total = total;
  }

  public CategoryPurchasesDto() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<ProductDto> getPurchaseProducts() {
    return products;
  }

  public void setPurchaseProducts(List<ProductDto> purchaseProducts) {
    this.products = purchaseProducts;
  }

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }
}
