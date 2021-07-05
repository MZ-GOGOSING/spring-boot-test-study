package me.gogosing.support.exception;

import me.gogosing.support.code.ErrorCode;

public class EntityDeletionFailedException extends BusinessException {

  public EntityDeletionFailedException() {
    super(ErrorCode.ENTITY_DELETION_ERROR);
  }

  public EntityDeletionFailedException(ErrorCode errorCode) {
    super(errorCode);
  }

  public EntityDeletionFailedException(String message) {
    super(message);
  }

  public EntityDeletionFailedException(String message, Throwable cause) {
    super(message, cause);
  }
}
