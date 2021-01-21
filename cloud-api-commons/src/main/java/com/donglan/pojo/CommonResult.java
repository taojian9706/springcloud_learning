package com.donglan.pojo;

import java.io.Serializable;

import lombok.Data;

/**
 * @author TAOJIAN
 * @version 1.0
 * @since 2021-01-15 14:54:46
 */
@Data
public class CommonResult<T> implements Serializable {

    private Integer code;

    private String message;

    private T data;

    private CommonResult() {

    }

    private CommonResult(Integer code, String message, T date) {
        this.code = code;
        this.message = message;
        this.data = date;
    }

    public static CommonResult success(Object data) {
        return new CommonResult(200, "success", data);
    }

    public static CommonResult fail() {
        return new CommonResult(500, "fail", null);
    }
}
