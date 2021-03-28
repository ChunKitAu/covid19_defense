package com.oujunjie.covid19_defense.comm.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @auther ChunKitAu
 * @create 2021-03-27 27
 */
public class httpUtil {
    private static final Logger logger = LoggerFactory.getLogger(httpUtil.class);

    private  static RestTemplate client = new RestTemplate();
    // 发送http get 请求
    public static String sendGetRequest(String url, MultiValueMap<String, String> params){

        ResponseEntity<String> response = client.getForEntity(url,  String.class,params);
        return response.getBody();
    }

}
