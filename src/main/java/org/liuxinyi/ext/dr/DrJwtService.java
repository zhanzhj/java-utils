package org.liuxinyi.ext.dr;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.liuxinyi.utils.http.HttpConstants;
import org.liuxinyi.utils.http.HttpUtils;

import java.security.interfaces.RSAPrivateKey;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eric.Liu on 2016/11/11.
 */
@Slf4j
public class DrJwtService {

    private DrConfig drConfig;

    /**
     * 使用生成的JWT Assertion 通过http请求获取token 获取失败返回""
     *
     * @param assertion
     * @return
     */
    public String requestTokenByAssertion(String assertion) {
        String url = drConfig.getDrBaseUrl() + DrConstants.API_REQUEST_TOKEN;

        HttpClient httpClient = HttpUtils.buildCustomerHttpClient(200, 40);
        HttpPost httpPost = new HttpPost(url);
        HttpResponse response;
        try {
            //拼接参数
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("assertion", assertion));
            httpPost.setHeader("Content-type", HttpConstants.CONTENT_TYPE_FORM);
            httpPost.addHeader("Host", drConfig.getDrBaseUrl().substring(8));
            httpPost.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
            httpPost.setEntity(new UrlEncodedFormEntity(params));
            response = httpClient.execute(httpPost);
            response.getEntity();

            HttpEntity httpResponseEntity = response.getEntity();
            String body = EntityUtils.toString(httpResponseEntity, "UTF-8");

            //   Map<String, String> resultMap = JsonUtil.parseMap(body, String.class);
            //   Map<String, String> dataMap = JsonUtil.parseMap(resultMap.get("data"), String.class);
            //  String token_type = dataMap.get("token_type");
            //  String access_token = dataMap.get("access_token");
            // 获取成功返回token
            String token_type = "a", access_token = "b";
            if (!StringUtils.isBlank(token_type) && !StringUtils.isBlank(access_token)) {
                return token_type + " " + access_token;
            }
        } catch (Exception e) {
            log.error("failed to request token!", e);
        }
        //获取失败返回 空
        return "";
    }


    /**
     * @param jwtHeader jwtHeader
     * @param jwtClaim  jwtClaim
     * @return JWTAssertion
     * @throws Exception
     */
    public String getJWTAssertionByHeadAndClaims(JWTHeader jwtHeader, JWTClaim jwtClaim) throws Exception {
        String header = JSONObject.toJSONString(jwtHeader);
        String claim = JSONObject.toJSONString(jwtClaim);
        String source = Base64.encodeBase64String(header.getBytes("utf-8")) + "."
                + Base64.encodeBase64String(claim.getBytes("utf-8"));
        String privateKeyStr = drConfig.getDrPrivateKey();
        String sign = SHAUtils.encrypt(source);
        RSAPrivateKey rsaPrivateKey = RSAUtils.getPrivateKey(privateKeyStr);
        String encryptSign = RSAUtils.encrypt(sign, rsaPrivateKey);
        return source + "." + encryptSign;
    }

    /**
     * @param iat token 生效时间戳
     * @return token
     * @throws Exception
     */
    public String getJWTAssertionByIatTimestamp(long iat) throws Exception {
        JWTHeader jwtHeader = new JWTHeader();
        jwtHeader.setAlg(DrConstants.DR_ALG);
        jwtHeader.setTyp(DrConstants.DR_TYP);
        JWTClaim jwtClaim = new JWTClaim();
        jwtClaim.setClientId(drConfig.getDrClientId());
        jwtClaim.setAud(drConfig.getDrBaseUrl() + DrConstants.API_REQUEST_TOKEN);
        jwtClaim.setIat(iat);
        jwtClaim.setExp(iat + DrConstants.DR_JWT_TOKEN_VALID_TIME);
        return getJWTAssertionByHeadAndClaims(jwtHeader, jwtClaim);
    }
}
