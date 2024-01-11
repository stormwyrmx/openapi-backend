package com.weng.openapiserver.controller;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

class AuthControllerTest
{
    public static void main(String[] args) throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.createDefault())
        {
            HttpPost httpPost = new HttpPost("http://127.0.0.1:8080/api/auth/login");
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setHeader("Origin", "https://www.baidu.com"); // 设置Origin头模拟跨域请求

            String json = "{\"username\":\"weng\",\"password\":\"wengyehao123\"}";
            StringEntity entity = new StringEntity(json);
            httpPost.setEntity(entity);

            CloseableHttpResponse response = httpClient.execute(httpPost);
//            HttpEntity responseEntity = response.getEntity();
            String resultString = EntityUtils.toString(response.getEntity(), "UTF-8");


            if (resultString != null)
            {
//                String result = EntityUtils.toString(responseEntity);
                System.out.println(resultString);
            }
        }
    }


}