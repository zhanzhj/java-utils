package org.liuxinyi.utils.http;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.net.InetAddress;

/**
 * Created by Eric.Liu on 2016/11/10.
 */
@Slf4j
public class IpUtils {

    public static String getInnerIp() throws Exception {
        return InetAddress.getLocalHost().getHostAddress();
    }

    @Test
    public void test() {
        try {
            log.info(getInnerIp());
        } catch (Exception e) {
            log.error("exception ", e);
        }
    }
}
