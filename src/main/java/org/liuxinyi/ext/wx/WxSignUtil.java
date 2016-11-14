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
 * Created by Eric.Liu on 2016/11/14.
 */
@Slf4j
public class WxSignUtil {
    public static String getSigned(Object t) {
        try {
            String xmlString = JaxbUtils.convertToXml(t);
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
            log.error("failed to getSign by object : {} ", t.toString(), e);
        }
        return "";
    }

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
        return DigestUtils.md5Hex(xmlString).toUpperCase();
    }
}
