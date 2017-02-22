package org.liuxinyi.sms;


import org.liuxinyi.utils.common.Response;

/**
 * Created by Administrator on 2017/2/13 0013.
 */
public interface SmsService {

    Response<String> sendSms(String mobile, String content);
}
