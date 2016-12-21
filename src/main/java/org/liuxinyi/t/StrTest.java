package org.liuxinyi.t;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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


    @Getter
    class ABC {
        private String name;
        private String email;

        public ABC(String name, String email) {
            this.name = name;
            this.email = email;
        }

    }

    @Test
    public void test2() {
        String siteFrom = RandomStringUtils.randomAlphanumeric(48);
        if (!StringUtils.isBlank(siteFrom)) {
            if (siteFrom.length() > 50) {
                String sub = siteFrom.substring(0, 50);
                log.info(sub.length() + "");
            }
        }
    }

    @Test
    public void test3() {
        List<ABC> list = new ArrayList<>();
        list.add(new ABC("a","a@"));
        list.add(new ABC("b","b@"));
        list.add(new ABC("c","c@"));
        list.add(new ABC("d","d@"));
        list.add(new ABC("e","e@"));
        list.add(new ABC("f","f@"));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", "ok");
        jsonObject.put("list", list);
        log.info("json result is : {} ", jsonObject.toJSONString());
    }


}
