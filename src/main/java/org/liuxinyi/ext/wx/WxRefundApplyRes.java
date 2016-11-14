package org.liuxinyi.ext.wx;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.liuxinyi.utils.xml.adaptor.AdaptorCDATA;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

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
    @XmlJavaTypeAdapter(AdaptorCDATA.class)
    private String deviceInfo;
    @XmlElement(name = "transaction_id")
    @XmlJavaTypeAdapter(AdaptorCDATA.class)
    private String transactionId;
    @XmlElement(name = "out_trade_no")
    @XmlJavaTypeAdapter(AdaptorCDATA.class)
    private String outTradeNo;
    @XmlElement(name = "refund_id")
    @XmlJavaTypeAdapter(AdaptorCDATA.class)
    private String refundId;
    @XmlElement(name = "total_fee")
    private int totalFee;
    @XmlElement(name = "cash_fee")
    private int cashFee;
    @XmlElement(name = "refund_count")
    private int refundCount;
    @XmlElement(name = "out_refund_no_$n")
    @XmlJavaTypeAdapter(AdaptorCDATA.class)
    private String outRefundNoN;
    @XmlElement(name = "refund_id_$n")
    @XmlJavaTypeAdapter(AdaptorCDATA.class)
    private String refundIdN;
    @XmlElement(name = "refund_channel_$n")
    @XmlJavaTypeAdapter(AdaptorCDATA.class)
    private String refundChannelN;
    @XmlElement(name = "refund_fee_$n")
    private String refundFeeN;
    @XmlElement(name = "refund_status_$n")
    @XmlJavaTypeAdapter(AdaptorCDATA.class)
    private String refundStatusN;
    @XmlElement(name = "refund_recv_accout_$n")
    @XmlJavaTypeAdapter(AdaptorCDATA.class)
    private String refundRecvAccoutN;
}
