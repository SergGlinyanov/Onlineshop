package net.thumbtack.model;

import java.util.List;

public class Product {
  long id;
  String nameProduct;
  int price;
  int count;
  List<Category> categories;

  public Product(long id, String nameProduct, int price) {
    this.id = id;
    this.nameProduct = nameProduct;
    this.price = price;
  }

  public Product() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getNameProduct() {
    return nameProduct;
  }

  public void setNameProduct(String nameProduct) {
    this.nameProduct = nameProduct;
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

  public List<Category> getCategories() {
    return categories;
  }

  public void setCategories(List<Category> categories) {
    this.categories = categories;
  }
}
