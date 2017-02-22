package org.liuxinyi.t;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2016/12/29 0029.
 */
@Slf4j
public class SetTest {
    @Test
    public void test() {
        int count2 = (int) (100 * 1.2);
        log.info(count2 + "");
    }

    @Test
    public void test1() {
        BigDecimal bigDecimal = new BigDecimal(20);
        log.info(bigDecimal.toPlainString());
        BigDecimal bigDecimal2 = new BigDecimal("20");
        log.info(bigDecimal2.toPlainString());

        BigDecimal bigDecimal3 = new BigDecimal(20.0);
        log.info(bigDecimal3.toPlainString());
        BigDecimal bigDecimal4 = new BigDecimal("20.2");
        log.info(bigDecimal4.toPlainString());
    }


    @Test
    public void test2() {
        Set<String> packetCodes = new HashSet<>();
        packetCodes.add("a");
        packetCodes.add("b");
        packetCodes.add("c");
        packetCodes.add("d");
        String[] sa = new String[5];
        packetCodes.toArray(sa);
        log.info(JSON.toJSONString(sa));
    }
}
