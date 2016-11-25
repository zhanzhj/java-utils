package org.liuxinyi.demos.hibernate.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by liuxinyi on 2016/8/9.
 */
@Table(name = "FUND_SETTING_ITEM")
@Data
@Entity
public class FundSettingItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    /**
     * 优先级
     */
    @Column(name = "PRIORITY")
    private Integer priority;
    /**
     * 资金方
     */
    @Column(name = "FUND_ID")
    private Integer fundId;

    @Column(name = "FUND_ABBR_CODE", length = 20)
    private String fundAbbrCode;

}
