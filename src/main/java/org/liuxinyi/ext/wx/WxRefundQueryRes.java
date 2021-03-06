package org.liuxinyi.ext.wx;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.*;

/**
 * Created by Eric.Liu on 2016/11/14.
 */
@ToString
@Getter
@Setter
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"transactionId", "outTradeNo", "outRefundNo", "refundId", "refundFee", "totalFee", "cashFee"})
public class WxRefundQueryRes extends WxCommonRes{
    @XmlElement(name = "transaction_id")
    private String transactionId;
    @XmlElement(name = "out_trade_no")
    private String outTradeNo;
    @XmlElement(name = "out_refund_no")
    private String outRefundNo;
    @XmlElement(name = "refund_id")
    private String refundId;
    @XmlElement(name = "refund_fee")
    private int refundFee;
    @XmlElement(name = "total_fee")
    private int totalFee;
    @XmlElement(name = "cash_fee")
    private int cashFee;
}
