package org.liuxinyi.sms;

import lombok.Getter;

@Getter
public enum CommonStatusEnum {
    READY("READY", "准备发送"),
    SUCCESS("SUCCESS", "发送成功"),
    FAIL("FAIL", "发送失败"),;

    CommonStatusEnum(String status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    private String status;
    private String desc;
}
