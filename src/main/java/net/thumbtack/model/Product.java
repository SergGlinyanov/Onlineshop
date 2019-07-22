package net.thumbtack.model;

import java.util.List;

public class Product {
  long id;
  String name;
  int price;
  int count;
  List<Long> categories;

  public Product(long id, String name, int price) {
    this.id = id;
    this.name = name;
    this.price = price;
  }

  public Product(long id, String name, int price, int count,
      List<Long> categories) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.count = count;
    this.categories = categories;
  }

  public Product() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public List<Long> getCategories() {
    return categories;
  }

  public void setCategories(List<Long> categories) {
    this.categories = categories;
  }

  @Override
  public String toString() {
    return "Product{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", price=" + price +
        ", count=" + count +
        ", categories=" + categories +
        '}';
  }
}
