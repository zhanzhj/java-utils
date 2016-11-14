package org.liuxinyi.ext.wx;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.liuxinyi.utils.xml.adaptor.AdaptorCDATA;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Created by Eric.Liu on 2016/11/14.
 */
@ToString
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"returnCode", "returnMsg", "appId", "mchId", "nonceStr",
        "resultCode", "errCode", "errCodeDes", "sign"})
public class WxCommonRes {
    @XmlElement(name = "return_code")
    @XmlJavaTypeAdapter(AdaptorCDATA.class)
    private String returnCode;
    @XmlElement(name = "return_msg")
    @XmlJavaTypeAdapter(AdaptorCDATA.class)
    private String returnMsg;
    @XmlElement(name = "appid")
    @XmlJavaTypeAdapter(AdaptorCDATA.class)
    private String appId;
    @XmlElement(name = "mch_id")
    @XmlJavaTypeAdapter(AdaptorCDATA.class)
    private String mchId;
    @XmlElement(name = "nonce_str")
    @XmlJavaTypeAdapter(AdaptorCDATA.class)
    private String nonceStr;
    @XmlElement(name = "result_code")
    @XmlJavaTypeAdapter(AdaptorCDATA.class)
    private String resultCode;
    @XmlElement(name = "err_code")
    @XmlJavaTypeAdapter(AdaptorCDATA.class)
    private String errCode;
    @XmlElement(name = "err_code_des")
    @XmlJavaTypeAdapter(AdaptorCDATA.class)
    private String errCodeDes;
    @XmlElement(name = "sign")
    @XmlJavaTypeAdapter(AdaptorCDATA.class)
    private String sign;
}
