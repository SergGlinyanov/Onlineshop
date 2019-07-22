package net.thumbtack.model;

public class Admin {

  private long id;
  private String lastName;
  private String firstName;
  private String patronymic;
  private String login;
  private String password;
  private String position;


  public Admin(Long id, String lastName, String firstName, String patronymic, String login,
      String password, String position) {
    this.id = id;
    this.lastName = lastName;
    this.firstName = firstName;
    this.patronymic = patronymic;
    this.login = login;
    this.password = password;
    this.position = position;
  }

  public Admin() {
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

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  @Override
  public String toString() {
    return "Admin{" +
        "id=" + id +
        ", lastName='" + lastName + '\'' +
        ", firstName='" + firstName + '\'' +
        ", patronymic='" + patronymic + '\'' +
        ", login='" + login + '\'' +
        ", password='" + password + '\'' +
        ", position='" + position + '\'' +
        '}';
  }
}
