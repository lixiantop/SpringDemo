package org.example.springlearndemo.util;

/**
 * 统一API响应结果封装
 * @param <T> 数据类型
 */
public class RestResult<T> {
    // 响应状态码枚举
    public enum StatusCode {
        SUCCESS("200", "成功"),
        FAIL("500", "失败"),
        UNAUTHORIZED("401", "未授权"),
        FORBIDDEN("403", "禁止访问"),
        NOT_FOUND("404", "资源不存在"),
        SERVER_ERROR("500", "服务器内部错误");

        private final String code;
        private final String message;

        StatusCode(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }

    private String code;
    private String message;
    private T data;

    /**
     * 私有构造方法
     * @param code 状态码
     * @param message 消息
     * @param data 数据
     */
    private RestResult(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 获取状态码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置状态码
     */
    public RestResult<T> setCode(String code) {
        this.code = code;
        return this;
    }

    /**
     * 获取消息
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置消息
     */
    public RestResult<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * 获取数据
     */
    public T getData() {
        return data;
    }

    /**
     * 设置数据
     */
    public RestResult<T> setData(T data) {
        this.data = data;
        return this;
    }

    /**
     * 成功响应
     */
    public static <T> RestResult<T> success() {
        return new RestResult<>(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), null);
    }

    /**
     * 成功响应，带数据
     */
    public static <T> RestResult<T> success(T data) {
        return new RestResult<>(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), data);
    }

    /**
     * 成功响应，带数据和自定义消息
     */
    public static <T> RestResult<T> success(String message, T data) {
        return new RestResult<>(StatusCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 失败响应
     */
    public static <T> RestResult<T> fail() {
        return new RestResult<>(StatusCode.FAIL.getCode(), StatusCode.FAIL.getMessage(), null);
    }

    /**
     * 失败响应，带自定义消息
     */
    public static <T> RestResult<T> fail(String message) {
        return new RestResult<>(StatusCode.FAIL.getCode(), message, null);
    }

    /**
     * 失败响应，带状态码和消息
     */
    public static <T> RestResult<T> fail(String code, String message) {
        return new RestResult<>(code, message, null);
    }

    /**
     * 使用预定义状态码的响应
     */
    public static <T> RestResult<T> response(StatusCode statusCode) {
        return new RestResult<>(statusCode.getCode(), statusCode.getMessage(), null);
    }

    /**
     * 使用预定义状态码的响应，带数据
     */
    public static <T> RestResult<T> response(StatusCode statusCode, T data) {
        return new RestResult<>(statusCode.getCode(), statusCode.getMessage(), data);
    }

    /**
     * 使用预定义状态码的响应，带数据和自定义消息
     */
    public static <T> RestResult<T> response(StatusCode statusCode, String message, T data) {
        return new RestResult<>(statusCode.getCode(), message, data);
    }
}
