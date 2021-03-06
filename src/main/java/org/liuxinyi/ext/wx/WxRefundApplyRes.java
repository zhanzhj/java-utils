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
@XmlType(propOrder = {"deviceInfo", "transactionId", "outTradeNo", "refundId", "totalFee",
        "cashFee", "refundCount","outRefundNoN","refundIdN","refundChannelN",
        "refundFeeN","refundStatusN","refundRecvAccoutN"})
public class WxRefundApplyRes extends WxCommonRes {
    @XmlElement(name = "device_info")
    private String deviceInfo;
    @XmlElement(name = "transaction_id")
    private String transactionId;
    @XmlElement(name = "out_trade_no")
    private String outTradeNo;
    @XmlElement(name = "refund_id")
    private String refundId;
    @XmlElement(name = "total_fee")
    private int totalFee;
    @XmlElement(name = "cash_fee")
    private int cashFee;
    @XmlElement(name = "refund_count")
    private int refundCount;
    @XmlElement(name = "out_refund_no_$n")
    private String outRefundNoN;
    @XmlElement(name = "refund_id_$n")
    private String refundIdN;
    @XmlElement(name = "refund_channel_$n")
    private String refundChannelN;
    @XmlElement(name = "refund_fee_$n")
    private String refundFeeN;
    @XmlElement(name = "refund_status_$n")
    private String refundStatusN;
    @XmlElement(name = "refund_recv_accout_$n")
    private String refundRecvAccoutN;
}
