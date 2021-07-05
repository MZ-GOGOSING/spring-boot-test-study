package me.gogosing.support.code;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum ErrorCode {

  SUCCESS("2000", "OK"),
  ACCOUNT_PASSWORD_EXPIRED("2101", "비밀번호 기간이 만료된 계정"),

  /* 1001 ~ 2000 logic error*/
  SYSTEM_PERMISSION_ERROR("1001", "시스템 권한 오류"),
  SYSTEM_STATUS_ERROR("1002", "시스템 상태 이상"),
  CODE_NOT_FOUND("1003", "코드를 찾을 수 없음"),
  INVALID_PARAMETER("1004", "유효성 확인 실패"),

  REST_ACCESS_FAIL("1101", "로그인 필요"),
  SESSION_CHANGED("1102", "세션 변경"),
  NOT_AGREE_REQUEST("1103", "이용 동의 해주세요."),
  MEMBER_ALREADY_EXIST("1104", "이미 동의 하셨습니다."),
  LOGIN_FAIL("1105", "로그인 실패"),
  ACCOUNT_LOCKED("1106", "잠겨 있는 계정"),
  ACCOUNT_EMAIL_UNAUTHENTICATED("1107", "이메일 인증되지 않은 계정"),
  ACCOUNT_INACTIVE("1108", "휴면 계정"),
  MIGRATION_ACCOUNT_LOCKED("1109", "이관대상자 잠겨 있는 계정"),

  REQUEST_NOT_FOUND("1404", "요청을 찾을 수 없습니다."),

  MANAGER_NOT_FOUND("8001", "관리자 사용자를 찾을수 없습니다."),

  /* 9001 ~  system error*/
  SYSTEM_ERROR("9001", "시스템 오류"),
  BAD_REQUEST_ERROR("9002", "부적절한 요청 오류"),
  UNAUTHORIZED_ERROR("9003", "인증 오류"),
  NO_SUCH_ENTITY_ERROR("9004", "존재하지 않는 엔티티 오류"),
  EXTERNAL_API_ERROR("9005", "외부 API 호출 에러 입니다."),
  PURCHASE_VALIDATION_FAILED("9006", "상품 구매를 위한 Validation 실패입니다."),
  ENTITY_EXISTS_ERROR("9007", "이미 존재하는 데이터입니다."),
  ENTITY_SAVE_ERROR("9008", "데이터 저장에 실패하였습니다."),
  ENTITY_DELETION_ERROR("9009", "데이터 삭제에 실패하였습니다."),
  NO_SUCH_ITEM_CODE_ERROR("9010", "ItemCode가 존재하지 않습니다."),
  TOKEN_EXPIRED_ERROR("9011", "토큰의 사용 기한이 만료 되었습니다."),
  TOKEN_AUTHENTICATED("9012", "이미 인증이 완료되었습니다."),
  LINK_EXPIRED_ERROR("9013", "링크가 만료 되었습니다."),
  TOKEN_EXPIRED_OR_UNUSABLE_ERROR("9014", "토큰의 사용 기한이 만료 되었거나 사용할수 없습니다."),
  TOKEN_UNUSABLE_ERROR("9015", "토큰이 이미 사용되어지거나 재발급되어 사용할수 없습니다."),
  UNKNOWN_ERROR("9999", "알 수 없는 오류");

  private final String code;
  private final String defaultMessage;

  ErrorCode(String code, String defaultMessage) {
    this.code = code;
    this.defaultMessage = defaultMessage;
  }

}
