package org.liuxinyi.sms;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "T_CRM_SMS")
@Entity
@Getter
@Setter
@ToString
public class Sms {
    @Id
    private String id;
    @Column
    private String mobile;
    @Column
    private String content;
    @Column
    private String result;
    @Column(name = "CREATE_TIME")
    private Date createTime;
    @Column(name = "UPDATE_TIME")
    private Date updateTime;
    @Column(name = "SUCCESS_TIME")
    private Date successTime;
    @Column(name = "MSG_ID")
    private String msgId;
    @Column(name = "THIRD_MSG_ID")
    private String thirdMsgId;
    // since 17-02-20
    @Column(name = "CHANNEL")
    private String channel;
}
