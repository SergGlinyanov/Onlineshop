package net.thumbtack.dto;

public class ResponseCategoryDto {

  private long id;
  private String name;
  private long idParentCategory;
  private String parentName;

  public ResponseCategoryDto(long id, String name, long idParentCategory, String parentName) {
    this.id = id;
    this.name = name;
    this.idParentCategory = idParentCategory;
    this.parentName = parentName;
  }

  public ResponseCategoryDto() {
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

  public String getParentName() {
    return parentName;
  }

  public void setParentName(String parentName) {
    this.parentName = parentName;
  }
}
