package net.thumbtack.model;

abstract class GlobalUser {

  private String lastName;
  private String firstName;
  private String patronymic;
  private String login;
  private String password;

  public GlobalUser(String lastName, String firstName, String patronymic, String login,
      String password) {
    this.lastName = lastName;
    this.firstName = firstName;
    this.patronymic = patronymic;
    this.login = login;
    this.password = password;
  }
}
