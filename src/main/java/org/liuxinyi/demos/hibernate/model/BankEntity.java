package org.liuxinyi.demos.hibernate.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by liuxinyi on 2016/8/4.
 */
@Table(name = "BANK")
@Entity
@Data
public class BankEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "BANK_NAME", length = 50)
    private String bankName;
    @Column(name = "BANK_CODE", unique = true, length = 30)
    private String bankCode;
    @Column(name = "ABBR_CODE", length = 30)
    private String abbrCode;
    @Column(name = "MESSAGE", length = 100)
    private String message;
}
