package org.liuxinyi.t;


import cz.mallat.uasparser.OnlineUpdater;
import cz.mallat.uasparser.UASparser;
import cz.mallat.uasparser.UserAgentInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class UaTest {

    @Test
    public void test1() {
        try {

            UASparser parser = new UASparser(OnlineUpdater.getVendoredInputStream());
            UserAgentInfo info = parser.parse("Mozilla/4.0 (compatible; MSIE 7.0;Windows NT 5.1; )");
            log.info(info.getDeviceType());
        } catch (Exception e) {
            log.error("failed ", e);
        }
    }
}
