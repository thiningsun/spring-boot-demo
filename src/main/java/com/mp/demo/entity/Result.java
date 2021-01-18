package com.mp.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Result<T> implements Serializable {

    public static final int SUCCESS = 0;
    public static final int ERROR = 1;
    public static final int NOT_LOGIN = 999;

    private static final long serialVersionUID = -3032015199552656978L;
    private int code;
    private String message = "";
    private T data;

    public static <T> Result<T> create(int code, String message) {
        return new Result<>(code, message, null);
    }

    public static <T> Result<T> create(int code, String message, T data) {
        return new Result<>(code, message, data);
    }

    public static <T> Result<T> successData(String message, T data) {
        return create(SUCCESS, message, data);
    }

    public static <T> Result<T> successMessage(String message) {
        return successData(message, null);
    }

    public static <T> Result<T> successData(T data) {
        return successData("success", data);
    }

    public static <T> Result<T> success() {
        return successMessage((String) null);
    }

    public static <T> Result<T> errorData(String message, T data) {
        return create(ERROR, message, data);
    }

    public static <T> Result<T> error() {
        return errorMessage((String) null);
    }

    public static <T> Result<T> errorData(T data) {
        return errorData((String) null, data);
    }

    public static <T> Result<T> errorMessage(String message) {
        return errorData(message, null);
    }

    public static <T> Result<T> selectiveMessage(boolean success, String successMessage, String errorMessage) {
        return success ? successMessage(successMessage) : errorMessage(errorMessage);
    }

    public static <T> Result<T> selectiveMessage(boolean success, String successMessage, String errorMessage, T data) {
        return success ? successData(successMessage, data) : errorMessage(errorMessage);
    }

    public static <T> Result<T> error(Error e) {
        return e == null ? null : create(ERROR, e.getMessage());
    }

    public static <T> Result<T> error(Exception e) {
        return e == null ? null : create(ERROR, e.getMessage());
    }
}
