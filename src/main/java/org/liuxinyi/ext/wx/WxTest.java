package org.liuxinyi.ext.wx;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.liuxinyi.utils.xml.jaxb.JaxbUtils;

/**
 * Created by Eric.Liu on 2016/11/14.
 */
@Slf4j
public class WxTest {


    @Test
    public void test1() {
        WxRefundApplyReq apply = new WxRefundApplyReq();
        apply.setAppId("appid");
        apply.setMchId("mch_id");
        apply.setNonceStr("nonce_str");
        apply.setOutTradeNo("out_trade_no");
        apply.setOutRefundNo("out_refund_no");
        apply.setTotalFee(1);
        apply.setRefundFee(1);
        apply.setOpUserId("op_user_id");
        long start = System.currentTimeMillis();
        String sign = WxSignUtil.getSignString(apply);
        log.info("sign : {} ", sign);
        apply.setSign(sign);
        long end = System.currentTimeMillis();
        log.info("cost : {} ", end - start);
        try {
            String xml = JaxbUtils.convertToXml(apply);
            log.info("xml : {}", xml);
        } catch (Exception e) {

        }
    }
}
