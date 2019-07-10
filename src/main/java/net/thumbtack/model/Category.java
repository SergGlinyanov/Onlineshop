package net.thumbtack.model;

public class Category {

  private long id;
  private String name;
  private long idParentCategory;

  public Category(long id, String name, int idParentCategory) {
    this.id = id;
    this.name = name;
    this.idParentCategory = idParentCategory;
  }

  public Category() {
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

  public long getIdParentCategory() {
    return idParentCategory;
  }

  public void setIdParentCategory(long idParentCategory) {
    this.idParentCategory = idParentCategory;
  }
}
