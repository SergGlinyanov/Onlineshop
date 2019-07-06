package net.thumbtack.model;

public class Category {

  private String name;
  private String parentCategory;

  public Category(String name, String parentCategory) {
    this.name = name;
    this.parentCategory = parentCategory;
  }

  public Category() {
  }
}
