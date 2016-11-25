package org.liuxinyi.demos.hibernate.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * 海尔对应的银行信息
 * Created by Eric.Liu on 2016/10/21.
 */
@Entity
@Table(name = "HR_BANK_INFO")
@Getter
@Setter
@ToString
public class HrBankInfoEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 联行号 对应excel的开户行号
     */
    @Column(name = "BANK_UNION_NO", length = 30)
    private String bankUnionNo;
    /**
     * 银行代码
     */
    @Column(name = "BANK_CODE", length = 30)
    private String bankCode;
    /**
     * 银行名称
     */
    @Column(name = "BANK_NAME", length = 30)
    private String bankName;
    /**
     * 支付行清算行号
     */
    @Column(name = "LIQUIDATION_NO", length = 30)
    private String liquidationNo;
    /**
     * 开户行名称
     */
    @Column(name = "BANK_BRANCH_NAME", length = 50)
    private String bankBranchName;
}
