package net.thumbtack.dto;

public class ProductDto {

  long id;
  String name;
  int price;
  int count;

  public ProductDto(long id, String name, int price, int count) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.count = count;
  }

  public ProductDto() {
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

  @Override
  public String toString() {
    return "ProductDto{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", price=" + price +
        ", count=" + count +
        '}';
  }
}
