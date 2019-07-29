package net.thumbtack.model;

public class Category {

  private long id;
  private String name;
  private long parentId;

  public Category(long id, String name, long parentId) {
    this.id = id;
    this.name = name;
    this.parentId = parentId;
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

  public long getParentId() {
    return parentId;
  }

  public void setParentId(long parentId) {
    this.parentId = parentId;
  }

  @Override
  public String toString() {
    return "Category{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", parentId=" + parentId +
        '}';
  }
}
