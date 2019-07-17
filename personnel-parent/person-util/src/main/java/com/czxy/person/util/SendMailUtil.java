package com.czxy.person.util;

import org.apache.http.HttpResponse;

import java.util.HashMap;
import java.util.Map;

public class SendMailUtil {

    /**
     * 短信测试
     *
     * @param args
     */
    public static void main(String[] args) {

        try {
            SendMailUtil.sendMail("1314","18570141922");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void sendMail(String code,String telephone) {
        String host = "https://smsapi.api51.cn";
        String path = "/code/";
        String method = "GET";
        String appcode = "e48704c590be441082ad5a43d4275c92";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("code",code);
        querys.put("mobile", telephone);

        try {
            /**
             * 重要提示如下:
             * HttpUtil请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtil.doGet(host, path, method, headers, querys);
            System.out.println(response.toString());
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
