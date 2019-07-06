package net.thumbtack.model;

import java.util.Map;

public class User extends GlobalUser {

  private String email;
  private String address;
  private String phone;
  private int deposit;
  private Map<Product, Integer> basket;

  public User(String lastName, String firstName, String patronymic, String email,
      String postalAddress, String phoneNumber, String login,
      String password) {
    super(lastName, firstName, patronymic, login, password);
    this.email = email;
    this.address = postalAddress;
    this.phone = phoneNumber;
  }
}
