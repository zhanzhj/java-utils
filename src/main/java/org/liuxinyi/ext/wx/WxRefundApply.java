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
@XmlType(propOrder = {"appId", "mchId","nonceStr","outTradeNo","outRefundNo","totalFee","refundFee","opUserId","sign"})
public class WxRefundApply {
    @XmlElement(name = "appid")
    private String appId;
    @XmlElement(name = "mch_id")
    private String mchId;
    @XmlElement(name = "nonce_str")
    private String nonceStr;
    @XmlElement(name = "sign")
    private String sign;
    @XmlElement(name = "out_trade_no")
    private String outTradeNo;
    @XmlElement(name = "out_refund_no")
    private String outRefundNo;
    @XmlElement(name = "total_fee")
    private String totalFee;
    @XmlElement(name = "refund_fee")
    private String refundFee;
    @XmlElement(name = "op_user_id")
    private String opUserId;

}
