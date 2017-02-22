package org.liuxinyi.sms;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "T_CRM_SMS_WHITE")
@Entity
@Getter
@Setter
@ToString
public class SmsWhite {
    @Id
    private String mobile;
    @Column(name = "CREATE_TIME")
    private Date createTime;
    @Column(name = "NAME")
    private String name;
}
