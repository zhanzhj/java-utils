package org.liuxinyi.demos.hibernate.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 资金方匹配记录
 * Created by liuxinyi on 2016/8/4.
 */
@Table(name = "FUND_MATCH")
@Entity
@Data
public class FundMatchEntity {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    /**
     * 申请编号
     */
    @Column(name = "APPLY_NO", length = 30)
    private String applyNo;
    /**
     * 用户号
     */
    @Column(name = "MEMBER_ID")
    private Long memberId;
    /**
     * 申请金额
     */
    @Column(name = "LOAN_AMOUNT")
    private Integer loanAmount;
    /**
     * 商户编码
     */
    @Column(name = "MERCHANT_CODE", length = 20)
    private String merchantCode;
    /**
     * 匹配资金方编码
     */
    @Column(name = "FUND_CODE", length = 20)
    private String fundCode;
    /**
     * 是否需要卡鉴权
     */
    @Column(name = "CARD_CERTIFICATE")
    private String cardCertificate;
    /**
     * 是否需要代扣
     */
    @Column(name = "CARD_WITHHOLD")
    private boolean cardWithhold;
    /**
     * 申请的状态
     */
    @Column(name = "APPLY_STATUS", length = 20)
    private String applyStatus;
    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME", updatable = false)
    private Date createTime;
    /**
     * 更新时间
     */
    @Column(name = "UPDATE_TIME")
    private Date updateTime;
    /**
     * 推送时间
     */
    @Column(name = "PUSH_TIME")
    private Date pushTime;
    /**
     * 资金方放款时间
     */
    @Column(name = "FUND_PAY_TIME")
    private Date fundPayTime;
    /**
     * 米么放款到商户时间
     */
    @Column(name = "MIME_PAY_TIME")
    private Date mimePayTime;
    /**
     * 期限
     */
    @Column(name = "TERM")
    private Integer term;
}
