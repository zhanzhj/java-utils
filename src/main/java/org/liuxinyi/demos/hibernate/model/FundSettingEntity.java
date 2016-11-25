package org.liuxinyi.demos.hibernate.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;


/**
 * 资金方配置
 * Created by liuxinyi on 2016/8/9.
 */
@Table(name = "FUND_SETTING")
@Entity
@Data
public class FundSettingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NAME", length = 200)
    private String name;
    @Column(name = "DESCRIPTION", length = 100)
    private String description;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "FUND_SETTING_ID")
    private List<FundSettingItemEntity> fundSettingItemEntities;
}
