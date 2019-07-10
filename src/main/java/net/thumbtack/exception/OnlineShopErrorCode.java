package net.thumbtack.exception;

public enum OnlineShopErrorCode {

  USER_WRONG_LOGIN("Логин должен содеожать от 3 до 16 латинских букв и цифр."),
  USER_WRONG_PASSWORD("Пароль должен содеожать от 6 до 18 латинских букв и цифр."),
  LOGIN_ALREADY_EXISTS("Пользователь с таким логином уже есть.");

  String errorString;

  OnlineShopErrorCode(String message) {
    this.errorString = message;
  }

  public String getErrorString() {
    return errorString;
  }
}
