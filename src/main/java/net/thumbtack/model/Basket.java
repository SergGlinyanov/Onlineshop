package net.thumbtack.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Basket {
  long id;
  Map<Product, Integer> basket;

  public Basket(long id, Map<Product, Integer> basket) {
    this.id = id;
    this.basket = basket;
  }

  public Basket() {
  }

  public Map<Product, Integer> getBasket() {
    return basket;
  }

  public void setBasket(Map<Product, Integer> basket) {
    this.basket = basket;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
}
