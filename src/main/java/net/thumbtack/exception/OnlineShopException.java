package net.thumbtack.exception;

public class OnlineShopException extends RuntimeException {

  private OnlineShopErrorCode onlineShopErrorCode;

  public OnlineShopException(OnlineShopErrorCode onlineShopErrorCode) {
    this.onlineShopErrorCode = onlineShopErrorCode;
  }

  public OnlineShopErrorCode getErrorCode() {
    return onlineShopErrorCode;
  }

  public String toString() {
    return onlineShopErrorCode.getErrorString();
  }

}
