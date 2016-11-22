package org.liuxinyi.utils.md;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import java.security.Provider;
import java.security.Security;
import java.util.Map;

/**
 * Created by Eric.Liu on 2016/11/22.
 */
@Slf4j
public class MdTest {

    @Test
    public void test() {
        String origin = "commons codec is very good at message digest. ";
        String md5 = DigestUtils.md5Hex(origin);
        log.info("md5 : {} ", md5);
        String sha384 = DigestUtils.sha384Hex(origin);
        log.info("sha384 : {} ", sha384);
        String sha512 = DigestUtils.sha512Hex(origin);
        log.info("sha512 : {} ", sha512);
    }

    @Test
    public void test1() {
        String origin = "commons codec is very good at base64. ";
        String base64String = Base64.encodeBase64String(origin.getBytes());
        log.info("base64String : {} ", base64String);
        byte[] bytes = Base64.decodeBase64(base64String);
        String decode64 = new String(bytes);
        log.info("decode64 : {} ", decode64);
    }

    @Test
    public void test2() {
        Provider[] providers = Security.getProviders();
        for (Provider provider : providers) {
            log.info("provider : " + provider.getName() + provider.getInfo() + provider.getVersion());
            for (Map.Entry entry : provider.entrySet()) {
                log.info(" key : " + entry.getKey() + " value : " + entry.getValue());
            }
        }
    }

}
