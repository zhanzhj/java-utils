package org.liuxinyi.t;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

/**
 * Created by Administrator on 2016/12/13 0013.
 */
@Slf4j
public class StrTest {
    @Test
    public void test1() {
        String inviteCode = RandomStringUtils.randomAlphanumeric(8).toUpperCase();
        log.info("inviteCode : {} ", inviteCode);
    }
}
