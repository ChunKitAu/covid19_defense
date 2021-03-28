package com.oujunjie.covid19_defense.covid.amap.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @auther ChunKitAu
 * @create 2021-03-28 28
 */
@Component
public class AmapConfig {

    public static  String key;
    public static  String sid;


    @Value("${AmapKey}")
    public void setKey(String key) {
        this.key = key;
    }

    @Value("${SId}")
    public void setSid(String sid) {
        AmapConfig.sid = sid;
    }
}