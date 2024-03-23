package com.weng.openapimap;

import jakarta.annotation.Resource;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.http.HttpClient;

@SpringBootTest
class OpenApiMapApplicationTests {

    @Resource
    private RestTemplate restTemplate;

    @Value("${baidu.ak}")
    private String ak;

    @Test
    void testGeoConv() throws IOException {
        ResponseEntity<String> exchange
                = restTemplate.exchange("https://api.map.baidu.com/geoconv/v1/?" +
                        "coords=114.21892734521,29.575429778924&from=1&to=5&ak="+ak,
                        HttpMethod.GET, null, String.class);
        System.out.println(exchange.getBody());
    }

    //todo 智能硬件定位
    @Test
    void testLocApi(){
        // 创建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 创建请求体
        String requestBody = """
                {
                        "ver": "1.0",
                        "trace": false,
                        "prod": "test_loc_api",
                        "src": "baidu_loc_api",
                          "key": "N9k2Q0OCY62Ao9FHlLyfGcGZ94Fq3aaP",
                        "body": [{
                            "bts": "460,0,4189,8869,-63",
                            "output": "JSON",
                            "accesstype": 0,
                      "macs":"70:ba:ef:d0:87:91,-42,|70:ba:ef:d1:0e:01,-45,|70:ba:ef:cc:98:11,-56,|dc:fe:18:c9:94:ce,-87,|58:60:5f:68:d8:30,-89,|94:d9:b3:cf:a2:db,-91,|30:fc:68:ac:c6:ae,-91,|c8:3a:35:32:48:c8,-94,",
                            "imei": "xxxxxxxxxxxxxxx",
                            "ctime": "1551178833",			
                      "nearbts":"460,0,4189,33989,-81|460,0,4189,8868,-83|460,0,4189,33988,-84|460,0,4189,239,-86|460,0,4189,32659,-98|460,0,4189,8867,-99",
                            "cdma": 0,
                            "need_rgc": "Y",
                            "network": "GPRS"
                          }
                        ]
                      }""";

        // 创建 HttpEntity 对象
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> exchange
                = restTemplate.exchange("https://api.map.baidu.com/locapi/v2?" +
                        "ver=1.0&trace=false&prod=test_loc_api&src=baidu_loc_api&key="+ak,
                HttpMethod.POST, entity, String.class);
        System.out.println(exchange.getBody());
    }

    @Test
    void testLocIp(){
        ResponseEntity<String> exchange
                = restTemplate.exchange("https://api.map.baidu.com/location/ip?" +
                        "ip=111.206.214.37&coor=bd09ll&ak=" + ak,
                HttpMethod.GET, null, String.class);
        System.out.println(exchange.getBody());
    }

    @Test
    void testSuggestion(){
        ResponseEntity<String> exchange
                = restTemplate.exchange("https://api.map.baidu.com/place/v2/suggestion?" +
                        "query=秋叶原&region=太仓&city_limit=true&output=json&ak=" + ak,
                HttpMethod.GET, null, String.class);
        System.out.println(exchange.getBody());
    }

    @Test
    void testDirection(){
        ResponseEntity<String> exchange
                = restTemplate.exchange("https://api.map.baidu.com/directionlite/v1/driving?" +
                        "origin=22.369084,114.14738&destination=22.2845,114.17088&ak=" + ak,
                HttpMethod.GET, null, String.class);
        System.out.println(exchange.getBody());
    }
}
