package org.liuxinyi.t;

import lombok.Getter;
import lombok.ToString;

/**
 * Created by Administrator on 2017/1/11 0011.
 */
@Getter
@ToString
public enum CommonStatus {
    ACTIVE("ACTIVE", "正常"),
    ENDED("ENDED", "已结束"),
    INACTIVE("INACTIVE", "不活动"),
    DENIED("DENIED", "拒绝"),
    STOPPED("STOPPED", "中止");

    private CommonStatus(String status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    private String status;
    private String desc;
}
