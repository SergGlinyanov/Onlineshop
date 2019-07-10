package net.thumbtack.model;

import java.util.Map;

public class Client {

  private long id;
  private String lastName;
  private String firstName;
  private String patronymic;
  private String email;
  private String address;
  private String phone;
  private String login;
  private String password;
  private int deposit;
  private Basket basket;

  public Client(Long id, String lastName, String firstName, String patronymic, String email,
      String address, String phone, String login, String password) {
    this.id = id;
    this.lastName = lastName;
    this.firstName = firstName;
    this.patronymic = patronymic;
    this.email = email;
    this.address = address;
    this.phone = phone;
    this.login = login;
    this.password = password;
  }

  public Client() {
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

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getDeposit() {
    return deposit;
  }

  public void setDeposit(int deposit) {
    this.deposit = deposit;
  }

  public Basket getBasket() {
    return basket;
  }

  public void setBasket(Basket basket) {
    this.basket = basket;
  }
}
