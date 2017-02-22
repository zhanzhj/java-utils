package org.liuxinyi.sms;


import lombok.extern.slf4j.Slf4j;
import org.liuxinyi.utils.common.Response;
import org.liuxinyi.utils.id.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class SmsServiceImpl implements SmsService {

    @Value(value = "${env}")
    private String env;

    private IdWorker idWorker = new IdWorker(3);

    @Autowired
    private SmsRepository smsRepository;

    @Autowired
    private SmsWhiteRepository smsWhiteRepository;

    @Override
    public Response<String> sendSms(String mobile, String content) {
        Sms sms = new Sms();
        sms.setId(idWorker.nextId() + "");
        sms.setCreateTime(new Date());
        sms.setResult("READY");
        sms.setMobile(mobile);
        sms.setContent(content);
        smsRepository.save(sms);

        String channel = "CL";
        sms.setChannel(channel);

        switch (env) {
            case "pro": {
                return sendProSms(sms);
            }
            case "uat": {
                return sendUatSms(sms);
            }
            default: {
                return sendUatSms(sms);
            }
        }

    }


    private Response<String> sendProSms(Sms sms) {
        try {
            Response<String> response = null;
            switch (sms.getChannel()) {
                case "CL": {
                    response = ClSmsUtil.sendSms(sms.getMobile(), sms.getContent());
                    break;
                }
                case "YT": {
                    response = YtSmsUtil.sendSms(sms.getMobile(), sms.getContent());
                    break;
                }
            }
            if (response.isSuccess()) {
                sms.setUpdateTime(new Date());
                sms.setResult(CommonStatusEnum.SUCCESS.getStatus());
                sms.setMsgId(response.getBody());
                smsRepository.save(sms);
                log.info("发送成功 sms : {} ", sms);
                return new Response<>("发送成功");
            } else {
                sms.setUpdateTime(new Date());
                sms.setResult(CommonStatusEnum.FAIL.getStatus());
                smsRepository.save(sms);
                log.info("发送失败 sms : {} ", sms);
                return new Response<>("500", "发送失败!");
            }

        } catch (Exception e) {
            log.error("发送失败 to send sms:{} ", sms, e);
            sms.setUpdateTime(new Date());
            sms.setResult(CommonStatusEnum.FAIL.getStatus());
            smsRepository.save(sms);
            return new Response<>("500", "发生未知错误" + e.getMessage());
        }
    }

    private Response<String> sendUatSms(Sms sms) {
        SmsWhite smsWhite = smsWhiteRepository.findOne(sms.getMobile());
        if (null == smsWhite) {
            return sendSitSms(sms);
        }
        return sendProSms(sms);
    }

    private Response<String> sendSitSms(Sms sms) {
        sms.setResult(CommonStatusEnum.SUCCESS.getStatus());
        sms.setUpdateTime(new Date());
        smsRepository.save(sms);
        log.info("发送成功 dev sms : {} ", sms);
        return new Response<>("发送成功");
    }


}
