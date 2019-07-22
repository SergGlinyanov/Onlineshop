package net.thumbtack.exception;

public class MyError {

  String errorCode;
  String field;
  String message;

  public MyError(String errorCode, String field, String message) {
    this.errorCode = errorCode;
    this.field = field;
    this.message = message;
  }

  public MyError() {
  }

  public String getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
