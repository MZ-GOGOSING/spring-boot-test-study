package me.gogosing.support.dto;

import me.gogosing.support.code.ErrorCode;

public class ApiResponseGenerator {

    private static final ApiResponse<Void> RESULT_SUCCESS = new ApiResponse<>(ErrorCode.SUCCESS);

    private static final ApiResponse<Void> RESULT_ERROR = new ApiResponse<>(ErrorCode.UNKNOWN_ERROR);

    private ApiResponseGenerator() {

    }

    public static ApiResponse<Void> success() {
        return RESULT_SUCCESS;
    }

    public static <D> ApiResponse<D> success(D data) {
        return new ApiResponse<>(ErrorCode.SUCCESS, null, data);
    }

    public static ApiResponse<Void> fail() {
        return RESULT_ERROR;
    }

    public static ApiResponse<Void> fail(ErrorCode code) {
        return new ApiResponse<>(code);
    }

    public static <D> ApiResponse<D> fail(ErrorCode code, D data) {
        return new ApiResponse<>(code, data);
    }

    public static ApiResponse<Void> fail(ErrorCode code, String msg) {
        return new ApiResponse<>(code, msg, null);
    }

    public static ApiResponse<String> fail(ErrorCode code, String msg, String errorCode) {
        return new ApiResponse<String>(code, msg, errorCode);
    }
}
