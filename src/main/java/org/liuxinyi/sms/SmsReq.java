package org.liuxinyi.sms;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class SmsReq implements Serializable{
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 短信内容
     */
    private String content;
}
