package net.thumbtack.model;

import java.util.Map;

public class BasketItems {
  long id;
  long id_product;
  int count;

  public BasketItems(long id, long id_product, int count) {
    this.id = id;
    this.id_product = id_product;
    this.count = count;
  }

  public BasketItems() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getId_product() {
    return id_product;
  }

  public void setId_product(long id_product) {
    this.id_product = id_product;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }
}
