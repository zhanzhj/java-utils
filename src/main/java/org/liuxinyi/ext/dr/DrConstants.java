package org.liuxinyi.ext.dr;

/**
 * Created by Johnson on 2016/2/1.
 */
public class DrConstants {

    // 贷款申请
    // 贷款申请生成接口
    public static final String API_URL_LOAN_APP = "/v1/mime/apps";

    // 申请状态查询
    // 根据流水号查询贷款申请状态
    public static final String API_URL_LOAN_STATUS_DRTXNO = "/v1/mime/apps/status?drtransactionNo=";
    // 根据第三方贷款提供的贷款id获取贷款申请状态
    public static final String API_URL_LOAN_STATUS_EXTID = "/v1/mime/apps/status?extId=";
    // 根据点融借款申请id查询贷款单审核状态
    public static final String API_URL_LOAN_STATUS_APPID = "/v1/mime/apps/appId/status";

    // 还款计划查询
    // 根据流水号查询还款计划 /v1/mime/loans/paymentPlan?drtransactionNo=
    public static final String API_URL_PAYMENT_PLAN_DRTXNO = "/v1/mime/loans/paymentPlan?drtransactionNo=";
    // 根据第三方贷款提供的贷款id查询还款计划  /v1/mime/loans/paymentPlan?extId=
    public static final String API_URL_PAYMENT_PLAN_EXTID = "/v1/mime/loans/paymentPlan?extId=";

    // 提交实际还款
    // 渠道提交实际还款信息 /v1/mime/loans/payment
    public static final String API_URL_LOAN_PAYMENT = "/v1/mime/loans/payment";

    // 申请token
    public static final String API_REQUEST_TOKEN = "/v1/oauth2/token";

    public static final long DR_JWT_TOKEN_VALID_TIME = 1000 * 60 * 60 * 2;

    public static final String DR_ALG = "RS256";

    public static final String DR_TYP = "JWT";

    public static final String REQUEST_TYPE = "requestType";

    public static final String DR_TOKEN_KEY = "gateway_dr_token_key";

    public static final String DR_TOKEN_LOCK_KEY = "gateway_dr_token_lock_key";

    public static final int DR_TOKEN_EXPIRE_SECONDS = 60 * 60;

}
