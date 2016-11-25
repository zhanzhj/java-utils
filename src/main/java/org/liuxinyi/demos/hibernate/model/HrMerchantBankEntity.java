package org.liuxinyi.demos.hibernate.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by Eric.Liu on 2016/10/11.
 */
@Entity
@Table(name = "HR_MERCHANT_BANK")
@Getter
@Setter
@ToString
public class HrMerchantBankEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    /**
     * 商户编码
     */
    @Column(name = "MERCHANT_CODE", length = 30)
    private String merchantCode;
    /**
     * 账户名
     */
    @Column(name = "ACCOUNT_NAME", length = 30)
    private String accountName;
    /**
     * 账号
     */
    @Column(name = "ACCOUNT_NO", length = 30)
    private String accountNo;
    /**
     * 开户行
     */
    @Column(name = "BANK", length = 30)
    private String bank;
    /**
     * 银行名称
     */
    @Column(name = "BANK_NAME", length = 30)
    private String bankName;
    /**
     * 开户行号 联行号
     */
    @Column(name = "BANK_NO", length = 30)
    private String bankNo;
    /**
     * 银行代码
     */
    @Column(name = "BANK_CODE", length = 30)
    private String bankCode;
    /**
     * 支付行清算行号
     */
    @Column(name = "LIQUIDATION_NO", length = 30)
    private String liquidationNo;

}
