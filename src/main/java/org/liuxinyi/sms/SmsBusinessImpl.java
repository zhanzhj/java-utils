package org.liuxinyi.sms;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.liuxinyi.utils.common.Response;
import org.liuxinyi.utils.redis.pool.RedisService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/1/15 0015.
 */
@Slf4j
public class SmsBusinessImpl {
    @Autowired
    private RedisService redisService;

    @Autowired
    private SmsService smsService;

    public Response<String> sendSms(SmsReq smsReq) {
        if (StringUtils.isBlank(smsReq.getContent())) {
            return new Response<>("400",
                    "短信内容为空");
        }
        if (!isMobile(smsReq.getMobile())) {
            return new Response<>("400",
                    "手机号码错误");
        }
        return smsService.sendSms(smsReq.getMobile(), smsReq.getContent());
    }

    public Response<String> sendSms(SmsReq smsReq, SmsLimit smsLimit) {
        // 参数检验
        if (null == smsReq) {
            return new Response<>("400",
                    "smsReq 不能为null");
        }
        if (null == smsLimit) {
            return new Response<>("400",
                    "smsLimit 不能为null");
        }
        if (StringUtils.isBlank(smsReq.getContent())) {
            return new Response<>("400",
                    "短信内容为空");
        }
        if (!isMobile(smsReq.getMobile())) {
            return new Response<>("400",
                    "手机号码错误");
        }

        if (StringUtils.isBlank(smsLimit.getBusinessId())) {
            return new Response<>("400",
                    "BusinessId为空");
        }

        if (smsLimit.getMaxTimes() < 1 || smsLimit.getMaxTimes() > 20) {
            return new Response<>("400",
                    "单位时间内的次数为1-20次");
        }
        if (smsLimit.getMinutes() < 1 || smsLimit.getMinutes() > 60 * 24 * 7) {
            return new Response<>("400",
                    "时间限制为1分钟到7天");
        }

        // 业务检验
        if (canSendSms(smsReq, smsLimit)) {
            return smsService.sendSms(smsReq.getMobile(), smsReq.getContent());
        }

        return new Response<>("500",
                "短信发送次数已超过单位时间内的最大限制!");
    }

    private boolean canSendSms(SmsReq smsReq, SmsLimit smsLimit) {
        int maxTime = smsLimit.getMaxTimes();
        int sentTimes = getSendCount(smsReq, smsLimit);
        if (sentTimes > maxTime) {
            return false;
        }
        return true;
    }


    private int getSendCount(SmsReq smsReq, SmsLimit smsLimit) {
        String key = smsLimit.getBusinessId() + smsReq.getMobile();
        long count = redisService.setNx(key, "1");
        if (count == 1) { // setNx success first time
            redisService.expire(key,
                    smsLimit.getMinutes() * 60);
        } else {
            count = redisService.increase(key);
        }
        return (int) count;
    }


    private static boolean isMobile(String mobile) {
        if (StringUtils.isBlank(mobile)) {
            return false;
        }
        if (Pattern.compile("^1[34578]\\d{9}$").matcher(mobile).matches()) {
            return true;
        }
        return false;
    }

}
