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
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"appId", "mchId","nonceStr","sign"})
public class WxCommonReq {
    @XmlElement(name = "appid")
    private String appId;
    @XmlElement(name = "mch_id")
    private String mchId;
    @XmlElement(name = "nonce_str")
    private String nonceStr;
    @XmlElement(name = "sign")
    private String sign;
}
