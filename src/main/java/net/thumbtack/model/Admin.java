package net.thumbtack.model;

public class Admin extends GlobalUser{

  private String position;


  public Admin(String lastName, String firstName, String patronymic, String position, String login,
      String password) {
    super(lastName, firstName, patronymic, login, password);
    this.position = position;
  }
}
