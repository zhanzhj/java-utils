package org.liuxinyi.t;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/12/19 0019.
 */
public class HttpCon {

    public static void main(String[] args) {
        testGet();
        // testPost();
    }

    private static void testGet() {
        String url = "http://localhost:9001/inviteCode/register/123";
        String result = restGet(url);
        JSONObject jsonObject = JSON.parseObject(result);
        String code = (String) jsonObject.get("code");
        if ("200".equals(code)) {
            String body = (String) jsonObject.get("t");
            System.out.println("OK :" + body);
        } else {
            String message = (String) jsonObject.get("message");
            System.out.println("fail :" + message);
        }
    }

    private static void testPost() {
        String url = "http://localhost:9001/inviteRecord/create";
        JSONObject param = new JSONObject();
        param.put("inviteCode", "LA90RY0O");
        param.put("invitedUserId", RandomStringUtils.randomAlphanumeric(10));
        param.put("userDeviceType", "PC");
        param.put("siteFrom", "badu.com");
        String jsonParam = param.toJSONString();
        String result = restPost(url, jsonParam);
        JSONObject jsonObject = JSON.parseObject(result);
        String code = (String) jsonObject.get("code");
        if ("200".equals(code)) {
            JSONObject body = jsonObject.getJSONObject("t");
            System.out.println("OK :" + body);
        } else {
            String message = (String) jsonObject.get("message");
            System.out.println("fail :" + message);
        }
    }


    public static String restGet(String path) {

        BufferedReader br = null;
        HttpURLConnection conn = null;
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(path);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);

            String lines = "";
            if (conn.getResponseCode() == 200) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                while ((lines = br.readLine()) != null)
                    sb.append(lines);
            }
        } catch (Exception e) {

        } finally {
            try {
                conn.disconnect();
                br.close();
            } catch (IOException e) {
            }
        }

        return sb.toString();
    }

    public static String restPost(String path, String content) {
        StringBuilder sb = new StringBuilder();
        HttpURLConnection con = null;
        OutputStreamWriter wr = null;
        BufferedReader br = null;
        try {

            URL url = new URL(path);

            con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestMethod("POST");


            wr = new OutputStreamWriter(con.getOutputStream());
            wr.write(content);
            wr.flush();

            int HttpResult = con.getResponseCode();
            if (HttpResult == HttpURLConnection.HTTP_OK) {
                br = new BufferedReader(
                        new InputStreamReader(con.getInputStream(), "utf-8"));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

                return sb.toString();
            } else {
                System.out.println(con.getResponseMessage());
            }
        } catch (Exception e) {

        } finally {
            try {
                con.disconnect();
                br.close();
                wr.close();
            } catch (Exception e) {

            }
        }
        return "";
    }
}
