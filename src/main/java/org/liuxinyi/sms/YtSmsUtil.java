package org.liuxinyi.sms;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.junit.Test;
import org.liuxinyi.utils.common.Response;
import org.liuxinyi.utils.http.HttpUtils;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * 移通发送短信工具
 *
 * @author liuxy
 */
@Slf4j
public class YtSmsUtil {
    private static final String mtUrl = "http://esms100.xxxx.net/sms/mt";
    private static final String spid = "69xx";
    private static final String sppassword = "kscfxxxx";

    private static final CloseableHttpClient defaultHttpClient;

    static {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(500);
        cm.setDefaultMaxPerRoute(300);
        defaultHttpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .build();
    }

    @Test
    public void testSend() {
        sendSms("1760210xxx5", "测试短信test23");
        //sendSms("xxx", "测试短信test");
    }

    /**
     * 短信发送
     *
     * @param mobile  手机号
     * @param content 短信内容
     */
    public static Response<String> sendSms(String mobile, String content) {
        // 发送http请求，并接收http响应
        Response<String> httpResponse = HttpUtils.get(generateUrl(mobile, content), null);

        // 网络成功
        if (httpResponse.isSuccess()) {
            String resStr = httpResponse.getBody();
            Map<String, String> resultMap = parseResStr(resStr);
            String resultCode = resultMap.get("mterrcode");
            if (StringUtils.equals(resultCode, "000")) {
                // 返回码 成功
                return new Response<>(resultMap.get("mtmsgid"));
            } else {
                log.error("网络成功,移通发送失败,mobile:{} ; content:{};resultMap:{}", mobile, content, resultMap);
            }
        }
        return new Response<>("500", "发送失败");
    }

    private static String generateUrl(String mobile, String content) {
        String command = "MT_REQUEST";// 单条发送
        // sp服务代码，可选参数，默认为 00
        String spsc = "00";
        // 源号码，可选参数
        String sa = "10657109053657";
        // 目标号码，必填参数
        String da = 86 + mobile;
        int dc = 15;
        String sm = encodeHexStr(dc, content);// 下行内容进行Hex编码，此处dc设为15，即使用GBK编码格式
        // 组成url字符串
        String smsUrl = mtUrl + "?command=" + command + "&spid=" + spid
                + "&sppassword=" + sppassword + "&spsc=" + spsc + "&sa=" + sa
                + "&da=" + da + "&sm=" + sm + "&dc=" + dc;//
        return smsUrl;
    }

    /**
     * 将 短信下行 请求响应字符串解析到一个HashMap中
     *
     * @param resStr
     * @return
     */
    private static HashMap parseResStr(String resStr) {
        HashMap pp = new HashMap();
        try {
            String[] ps = resStr.split("&");
            for (int i = 0; i < ps.length; i++) {
                int ix = ps[i].indexOf("=");
                if (ix != -1) {
                    pp.put(ps[i].substring(0, ix), ps[i].substring(ix + 1));
                }
            }
        } catch (Exception e) {
            log.error("failed to parseResStr", e);
        }
        return pp;
    }

    /**
     * Hex编码字符组
     */
    private static final char[] DIGITS = {'0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * 将普通字符串转换成Hex编码字符串
     *
     * @param dataCoding 编码格式，15表示GBK编码，8表示UnicodeBigUnmarked编码，0表示ISO8859-1编码
     * @param realStr    普通字符串
     * @return Hex编码字符串
     */
    private static String encodeHexStr(int dataCoding, String realStr) {
        String hexStr = null;

        if (realStr != null) {
            byte[] data = null;
            try {
                if (dataCoding == 15) {
                    data = realStr.getBytes("GBK");
                } else if ((dataCoding & 0x0C) == 0x08) {
                    data = realStr.getBytes("UnicodeBigUnmarked");
                } else {
                    data = realStr.getBytes("ISO8859-1");
                }
            } catch (UnsupportedEncodingException e) {
                log.error("failed to encode", e);
            }

            if (data != null) {
                int len = data.length;
                char[] out = new char[len << 1];
                // two characters form the hex value.
                for (int i = 0, j = 0; i < len; i++) {
                    out[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
                    out[j++] = DIGITS[0x0F & data[i]];
                }
                hexStr = new String(out);
            }
        }
        return hexStr;
    }


}

