package net.thumbtack.dto;

import java.util.List;

public class PurchaseFromBasketDto {

  List<ProductDto> bought;
  List<ProductDto> remaining;

  public PurchaseFromBasketDto(List<ProductDto> bought,
      List<ProductDto> remaining) {
    this.bought = bought;
    this.remaining = remaining;
  }

  public PurchaseFromBasketDto() {
  }

  public List<ProductDto> getBought() {
    return bought;
  }

  public void setBought(List<ProductDto> bought) {
    this.bought = bought;
  }

  public List<ProductDto> getRemaining() {
    return remaining;
  }

  public void setRemaining(List<ProductDto> remaining) {
    this.remaining = remaining;
  }
}
