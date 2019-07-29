package net.thumbtack.exception;

import java.util.List;

public class ErrorList {

List<MyError> errors;

  public ErrorList(List<MyError> Errors) {
    this.errors = Errors;
  }

  public ErrorList() {
  }

  public List<MyError> getErrors() {
    return errors;
  }

  public void setErrors(List<MyError> errors) {
    this.errors = errors;
  }

  @Override
  public String toString() {
    return "ErrorList{" +
        "errors=" + errors +
        '}';
  }
}
