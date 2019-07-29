package net.thumbtack.dto;

import net.thumbtack.model.Client;

public class ClientResponseDto {

  private long id;
  private String lastName;
  private String firstName;
  private String patronymic;
  private String email;
  private String address;
  private String phone;
  private int deposit;

  public ClientResponseDto(long id, String lastName, String firstName, String patronymic,
      String email, String address, String phone, int deposit) {
    this.id = id;
    this.lastName = lastName;
    this.firstName = firstName;
    this.patronymic = patronymic;
    this.email = email;
    this.address = address;
    this.phone = phone;
    this.deposit = deposit;
  }

  public ClientResponseDto(Client client) {
    this.id = client.getId();
    this.lastName = client.getLastName();
    this.firstName = client.getFirstName();
    this.patronymic = client.getPatronymic();
    this.email = client.getEmail();
    this.address = client.getAddress();
    this.phone = client.getPhone();
    this.deposit = client.getDeposit();
  }

  public ClientResponseDto() {
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public int getDeposit() {
    return deposit;
  }

  public void setDeposit(int deposit) {
    this.deposit = deposit;
  }
}
