package net.thumbtack.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Basket {
  Map<Product, Integer> basket;

  public Basket(Map<Product, Integer> basket) {
    this.basket = basket;
  }

  public Map<Product, Integer> getBasket() {
    return basket;
  }

  public void setBasket(Map<Product, Integer> basket) {
    this.basket = basket;
  }
}
