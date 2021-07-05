package me.gogosing.support.exception;

import me.gogosing.support.code.ErrorCode;

public class EntityExistsException extends BusinessException {

  public EntityExistsException() {
    super(ErrorCode.ENTITY_EXISTS_ERROR);
  }

  public EntityExistsException(ErrorCode errorCode) {
    super(errorCode);
  }

  public EntityExistsException(String message) {
    super(message);
  }

  public EntityExistsException(String message, Throwable cause) {
    super(message, cause);
  }
}
