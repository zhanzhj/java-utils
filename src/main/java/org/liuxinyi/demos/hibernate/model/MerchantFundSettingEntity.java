package org.liuxinyi.demos.hibernate.model;

import lombok.Data;

import javax.persistence.*;

/**
 * 商户和资金方配置的关系
 * Created by liuxinyi on 2016/8/9.
 */
@Table(name = "MERCHANT_FUND_SETTING")
@Entity
@Data
public class MerchantFundSettingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "MERCHANT_CODE", length = 20)
    private String merchantCode;
    @Column(name = "NAME", length = 50)
    private String name;
    @Column(name = "FUND_SETTING_ID")
    private Integer fundSettingId;
}
