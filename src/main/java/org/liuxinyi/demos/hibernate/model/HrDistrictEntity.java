package org.liuxinyi.demos.hibernate.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by Eric.Liu on 2016/10/9.
 */
@Table(name = "HR_DISTRICT")
@Entity
@Getter
@Setter
@ToString
public class HrDistrictEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "PROVINCE", length = 20)
    private String province;
    @Column(name = "CITY", length = 20)
    private String city;
    @Column(name = "DISTRICT", length = 30)
    private String district;
    @Column(name = "DISTRICT_CODE", length = 20)
    private String districtCode;
}
