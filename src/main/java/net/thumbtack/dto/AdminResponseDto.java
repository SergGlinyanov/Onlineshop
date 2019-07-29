package net.thumbtack.dto;

import net.thumbtack.model.Admin;

public class AdminResponseDto {

  private long id;
  private String lastName;
  private String firstName;
  private String patronymic;
  private String position;

  public AdminResponseDto(long id, String lastName, String firstName, String patronymic,
      String position) {
    this.id = id;
    this.lastName = lastName;
    this.firstName = firstName;
    this.patronymic = patronymic;
    this.position = position;
  }

  public AdminResponseDto(Admin admin) {
    this.id = admin.getId();
    this.lastName = admin.getLastName();
    this.firstName = admin.getFirstName();
    this.patronymic = admin.getPatronymic();
    this.position = admin.getPosition();
  }

  public AdminResponseDto() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getPatronymic() {
    return patronymic;
  }

  public void setPatronymic(String patronymic) {
    this.patronymic = patronymic;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }
}
