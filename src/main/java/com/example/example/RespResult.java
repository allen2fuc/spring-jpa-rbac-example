package com.example.example;

import lombok.Getter;

/**
 * @author fucheng on 2023/12/4
 */
@Getter
public class RespResult<T> {
    private final int code;
    private final String msg;
    private final T data;

    public RespResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    // 通用的返回成功的结果
    public static <T> RespResult<T> success(T data) {
        return new RespResult<>(0, "success", data);
    }

    // 通用的返回失败的结果
    public static <T> RespResult<T> fail(String msg) {
        return new RespResult<>(1, msg, null);
    }
}
