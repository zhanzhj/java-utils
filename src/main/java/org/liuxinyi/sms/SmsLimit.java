package org.liuxinyi.sms;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * businessId 全局唯一
 * minutes 分钟数为1分钟到7天
 * maxTimes 次数为1-20次
 */
@Getter
@Setter
@ToString
public class SmsLimit implements Serializable {

    private String businessId;
    private int minutes;
    private int maxTimes;


    public SmsLimit(String businessId, int minutes, int maxTimes) {
        this.businessId = businessId;
        this.minutes = minutes;
        this.maxTimes = maxTimes;
    }
}
