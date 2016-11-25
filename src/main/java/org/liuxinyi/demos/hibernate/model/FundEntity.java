package org.liuxinyi.demos.hibernate.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 资金方的全部信息
 * 包括基本信息 , 状态信息 ,  限制信息 , 最终优先级
 * Created by liuxinyi on 2016/8/1.
 */
@Table(name = "FUND")
@Entity
@Data
public class FundEntity {
    /**
     * 资金方ID
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 资金方编码
     */
    @Column(name = "FUND_CODE", length = 20)
    private String fundCode;
    /**
     * 资金方名称
     */
    @Column(name = "FUND_NAME", length = 100)
    private String fundName;
    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME", updatable = false)
    private Date createTime;
    /**
     * 修改时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_TIME")
    private Date updateTime;
    /**
     * 单笔最小金额限制
     */
    @Column(name = "SINGLE_MIN")
    private Integer singleMin;
    /**
     * 单笔最大金额限制
     */
    @Column(name = "SINGLE_MAX")
    private Integer singleMax;
    /**
     * 每日限额 单位元
     */
    @Column(name = "DAILY_LIMIT")
    private Integer dailyLimit;
    /**
     * 每月限额 单位万元
     */
    @Column(name = "MONTH_LIMIT")
    private Integer monthLimit;
    /**
     * 资金方状态
     */
    @Column(name = "STATUS", length = 20)
    private String status;
    /**
     * 资金必须是modAmount的倍数
     */
    @Column(name = "MOD_AMOUNT")
    private Integer modAmount;
    /**
     * 资金方支持的期数 用逗号分割
     */
    @Column(name = "SUPPORTED_TERMS", length = 100)
    private String supportedTerms;
    /**
     * 是否需要卡鉴权
     */
    @Column(name = "CARD_CERTIFICATE")
    private boolean cardCertificate;
    /**
     * 是否需要代扣
     */
    @Column(name = "CARD_WITHHOLD")
    private boolean cardWithhold;
    /**
     * 卡鉴权是否需要发送验证码
     */
    @Column(name = "SEND_CARD_CERTIFICATE_CODE")
    private boolean sendCardCertificateCode;
    /**
     * 用户有未结清的贷款不放款
     */
    @Column(name = "LOANED_REFUSE")
    private boolean loanedRefuse;
    /**
     * 卡鉴权银行编码
     */
    @Column(name = "BANK_CODES", length = 1000)
    private String bankCodes;
    /**
     * 资金方支持的还款方式
     */
    @Column(name = "REPAY_METHODS", length = 300)
    private String repayMethods;

    @Column(name = "ABBR_CODE", length = 20)
    private String abbrCode;

    @Column(name = "PAGE_H5")
    private String pageH5;

}
