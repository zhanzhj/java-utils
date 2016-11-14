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
@XmlType(propOrder = {"outTradeNo", "outRefundNo", "totalFee", "refundFee", "opUserId"})
public class WxRefundApplyReq extends WxCommonReq {
    @XmlElement(name = "out_trade_no")
    private String outTradeNo;
    @XmlElement(name = "out_refund_no")
    private String outRefundNo;
    @XmlElement(name = "total_fee")
    private int totalFee;
    @XmlElement(name = "refund_fee")
    private int refundFee;
    @XmlElement(name = "op_user_id")
    private String opUserId;

}
