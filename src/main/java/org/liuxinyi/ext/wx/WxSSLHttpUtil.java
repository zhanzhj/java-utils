/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */
package org.liuxinyi.ext.wx;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;

/**
 * 微信退款申请需要SSL证书
 * 不能设置 PoolingHttpClientConnectionManager build会失败
 */
@Slf4j
public class WxSSLHttpUtil {

    private static final int SOCKET_TIMEOUT = 15000;
    private static final int CONNECTION_TIMEOUT = 2000;
    private static final int CONNECTION_REQUEST_TIMEOUT = 1000;
    private static final String charSet = "UTF-8";

    private static RequestConfig requestConfig = RequestConfig.custom()
            .setSocketTimeout(SOCKET_TIMEOUT)
            .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT)
            .setConnectTimeout(CONNECTION_TIMEOUT).build();

    private static CloseableHttpClient httpclient = null;


    public static void main(String[] args) {
        try {
            String path = "C:/tmp/wx/wxPay.p12";
            String sec = "1406963902";
            String url = "https://api.mch.weixin.qq.com/secapi/pay/refund";
            String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><xml><appid>wx907da2acbab0a1f5</appid><mch_id>1406963902</mch_id><nonce_str>Ue2GQfT1an</nonce_str><sign>6241E28CAA1DE2BE22C3353DD1863E70</sign><out_trade_no>20161115954461</out_trade_no><out_refund_no>Ucds3X5BsTRs</out_refund_no><total_fee>1</total_fee><refund_fee>1</refund_fee><op_user_id>1406963902</op_user_id></xml> ";
            log.info(" before send ");

            initHttpsClient(path, sec);
            String res = post(url, xml);
            log.info(res);
        } catch (Exception e) {
            log.error("error ", e);
        }
    }

    public static void initHttpsClient(String certPath, String certPassword) {
        try {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            FileInputStream instream = new FileInputStream(new File(certPath));
            try {
                keyStore.load(instream, certPassword.toCharArray());
            } catch (Exception e) {
                log.error("failed to load keyStore ", e);
            } finally {
                instream.close();
            }

            // Trust own CA and all self-signed certs
            SSLContext sslcontext = SSLContexts.custom()
                    .loadKeyMaterial(keyStore, certPassword.toCharArray())
                    .build();
            // Allow TLSv1 protocol only
            HostnameVerifier hostnameVerifier = new DefaultHostnameVerifier();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                    sslcontext,
                    new String[]{"TLSv1"},
                    null,
                    new DefaultHostnameVerifier());
            // SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
            httpclient = HttpClients.custom()
                    .setSSLSocketFactory(sslsf)
                    .build();
            log.info(httpclient.toString());
        } catch (Exception e) {
            log.error("failed to initHttpsClient ", e);
        }
    }

    public static String post(String url, String body) {
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new StringEntity(body, charSet));
            httpPost.setConfig(requestConfig);

            CloseableHttpResponse response = httpclient.execute(httpPost);
            try {
                HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity, charSet);
            } finally {
                response.close();
            }
        } catch (Exception e) {
            log.error("failed to send post");
        }
        return "";
    }

}
