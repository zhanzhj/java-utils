package org.liuxinyi.utils.xml.jaxb;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.liuxinyi.utils.xml.bean.SingleBean;

/**
 * Created by Eric.Liu on 2016/11/11.
 */
@Slf4j
public class JaxbDemo {

    @Test
    public void test1() {
        SingleBean singleBean = new SingleBean();
        singleBean.setName("name1");
        singleBean.setEmail("123@163.com");
        try {
            String xml = JaxbUtils.convertToXml(singleBean);
            log.info("xml : {} ", xml);
        } catch (Exception e) {
            log.error("failed to convertToXml : {} ", singleBean, e);
        }
    }

    @Test
    public void test2() {
        try {
            String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><root><name><![CDATA[name1]]></name><email>123@163.com</email></root>";
            SingleBean singleBean = JaxbUtils.convertToJavaBean(xml, SingleBean.class);
            log.info("singleBean : {} ", singleBean);
        } catch (Exception e) {
            log.error("failed to convertToJavaBean", e);
        }
    }
}
