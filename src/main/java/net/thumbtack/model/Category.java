package net.thumbtack.model;

public class Category {

  private int id;
  private String name;
  private int idParentCategory;

  public Category(int id, String name, int idParentCategory) {
    this.id = id;
    this.name = name;
    this.idParentCategory = idParentCategory;
  }

  public Category() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getIdParentCategory() {
    return idParentCategory;
  }

  public void setIdParentCategory(int idParentCategory) {
    this.idParentCategory = idParentCategory;
  }
}
