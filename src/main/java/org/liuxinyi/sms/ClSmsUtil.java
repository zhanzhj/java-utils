package org.liuxinyi.sms;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.liuxinyi.utils.common.Response;
import org.liuxinyi.utils.http.HttpEntityUtils;
import org.liuxinyi.utils.http.HttpUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 创蓝发送短信工具类
 * Created by 刘心意
 */
@Slf4j
public class ClSmsUtil {

    private final static String send_sms_url = "http://222.73.xxx.156:80/msg/HttpBatchSendSM";
    private final static String query_balance_sms_url = "http://222.xxx.117.156:80/msg/QueryBalance";

    private final static Map<String, String> cl_keys = new HashMap<String, String>() {{
        put("account", "vip-xxxx");
        put("pswd", "Tch1xxxx");
    }};


    public static Response<String> sendSms(String mobile, String content) {
        Map<String, Object> resMap = null;
        try {

            Map params = new HashMap();
            params.put("mobile", mobile);
            params.put("msg", content);
            params.put("needstatus", "true");
            params.put("account", "vip-kscf");
            params.put("pswd", "Tch123456");
            HttpEntity entity = HttpEntityUtils.buildUrlEncodedFormEntity(params);
            Response<String> httpResponse = HttpUtils.postEntity(send_sms_url, null, entity);
            if (httpResponse.isSuccess()) {
                // 网络成功
                Map<String, String> resultMap = getSendResult(httpResponse.getBody());
                if (StringUtils.equals("0", resultMap.get("code"))) {
                    // 返回码成功
                    return new Response<>(resultMap.get("msgId"));
                } else {
                    log.error("网络成功,创蓝发送失败,mobile:{} ; content:{};resultMap:{}", mobile, content, resultMap);
                }
            }
        } catch (Exception e) {
            log.error("发送短信失败 mobile:{}, content:{} ", mobile, content, e);
        }
        return new Response<>("400", "发送短信失败");

    }

    private static Map<String, String> getSendResult(String res) {
        int index = res.indexOf(",");
        String code = res.substring(index + 1, index + 2);
        String timestamp = res.substring(0, index);
        int index2 = res.indexOf("\n");
        String msgId = res.substring(index2 + 1);
        Map<String, String> resultMap = new HashMap<>(4);
        resultMap.put("code", code);
        resultMap.put("timestamp", timestamp);
        resultMap.put("msgId", msgId);
        return resultMap;
    }


    public static Response<String> queryBalance() {
        HttpEntity entity = HttpEntityUtils.buildUrlEncodedFormEntity(cl_keys);
        Response<String> httpResponse = HttpUtils.postEntity(query_balance_sms_url, null, entity);
        if (httpResponse.isSuccess()) {
            // 网络成功
            Map<String, String> resultMap = getSendResult(httpResponse.getBody());
            log.info("resBody :{} ;resMap:{}", httpResponse.getBody(), resultMap);
            if (StringUtils.equals("0", resultMap.get("code"))) {
                // 返回码成功
                return new Response<>(resultMap.get("msgId"));
            } else {
                log.error("查询余额失败,resultMap:{}", resultMap);
            }
        }

        return new Response<>("400", "查询余额失败");
    }

}
