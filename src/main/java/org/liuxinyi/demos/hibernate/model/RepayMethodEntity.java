package org.liuxinyi.demos.hibernate.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Eric.Liu on 2016/8/17.
 */
@Table(name = "REPAY_METHOD")
@Entity
@Data
public class RepayMethodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "METHOD_CODE", length = 20)
    private String methodCode;
    @Column(name = "METHOD_NAME", length = 40)
    private String methodName;
    @Column(name = "REMARK", length = 40)
    private String remark;
}
