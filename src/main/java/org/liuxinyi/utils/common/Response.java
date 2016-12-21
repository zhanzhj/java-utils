package org.liuxinyi.utils.common;

import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
public class Response<T> implements Serializable {
    private boolean success = false;
    private String code;
    private String message;
    private T body;

    public Response(){
        super();
    }

    public Response(T body) {
        this.success = true;
        code = ApiCodeEnum.SUCCESS.getCode();
        message = ApiCodeEnum.SUCCESS.getMessage();
        this.body = body;
    }

    public Response(String code, String message, T body) {
        this.code = code;
        this.message = message;
        this.body = body;
    }

    public Response(String code, String message) {
        this.code = code;
        this.message = message;
    }
}

