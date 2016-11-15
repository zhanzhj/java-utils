package org.liuxinyi.ext.wx;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.liuxinyi.utils.xml.jaxb.JaxbUtils;

import java.util.Map;
import java.util.TreeMap;

/**
 * 微信APP支付签名工具类
 * Created by Eric.Liu on 2016/11/14.
 */
@Slf4j
public class WxSignUtil {
    /**
     * 获得微信的MD5签名
     *
     * @param obj obj
     * @return sign
     */
    public static String getSignString(Object obj) {
        try {
            String xmlString = JaxbUtils.convertToXml(obj);
            Document document = DocumentHelper.parseText(xmlString);
            Map<String, String> map = new TreeMap<>();
            Element root = document.getRootElement();
            for (Object object : root.elements()) {
                Element element = (Element) object;
                if ("sign".equals(element.getName())) {
                    continue;
                }
                map.put(element.getName(), element.getText());
            }
            return getSignByMap(map);
        } catch (Exception e) {
            log.error("failed to getSign by object : {} ", obj.toString(), e);
        }
        return "";
    }

    /**
     * 获得Map的MD5签名
     *
     * @param map map
     * @return MD5
     */
    private static String getSignByMap(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue());
            sb.append("&");
        }
        sb.append("key");
        sb.append("=");
        sb.append("key");
        String xmlString = sb.toString();
        log.info("xmlString : {} ", xmlString);
        return DigestUtils.md5Hex(xmlString).toUpperCase();
    }

    /**
     * 验证MD5签名
     *
     * @param obj  obj
     * @param sign sign
     * @return true if sign is same , false if not
     */
    public static boolean verifySign(Object obj, String sign) {
        String sign2 = getSignString(obj);
        if (sign.equals(sign2)) {
            return true;
        }
        return false;
    }
}
