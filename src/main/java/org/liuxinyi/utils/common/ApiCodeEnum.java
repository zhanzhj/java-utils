package org.liuxinyi.utils.common;

/**
 * api调用结果状态集
 * Created by devilmole on 2016/12/16 0016.
 */
public enum ApiCodeEnum {
    SUCCESS("200", "成功"), // 调用成功
    INVALID_PARAM("300", "参数错误"), // 参数错误
    BUSINESS_ERROR("400", "业务规则错误"), // 业务规则错误
    SYSTEM_ERROR("500", "系统错误"); // 系统错误

    ApiCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
