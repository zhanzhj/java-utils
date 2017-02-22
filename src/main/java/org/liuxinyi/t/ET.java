package org.liuxinyi.t;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created by Administrator on 2017/1/11 0011.
 */
@Slf4j
public class ET {
    @Test
    public void test() {
        log.info(CommonStatus.valueOf("ACTIVE") + "");
    }
}
