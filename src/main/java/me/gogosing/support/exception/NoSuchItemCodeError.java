package me.gogosing.support.exception;

import me.gogosing.support.code.ErrorCode;

public class NoSuchItemCodeError extends BusinessException {

  public static final String COMMA = ", ";

  public NoSuchItemCodeError() {
    super(ErrorCode.NO_SUCH_ITEM_CODE_ERROR);
  }

  public NoSuchItemCodeError(ErrorCode errorCode) {
    super(errorCode);
  }

  public NoSuchItemCodeError(String message) {
    super(message);
  }

  public NoSuchItemCodeError(String corporationNumber, String itemCode) {
    super(corporationNumber + COMMA + itemCode);
  }

  public NoSuchItemCodeError(String message, Throwable cause) {
    super(message, cause);
  }
}
