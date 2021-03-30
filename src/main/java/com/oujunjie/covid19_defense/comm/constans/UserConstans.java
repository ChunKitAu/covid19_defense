package com.oujunjie.covid19_defense.comm.constans;

/**
 * @auther ChunKitAu
 * @create 2021-03-29 29
 */
public class UserConstans {

    // header认证字段
    public static final String AUTHENTICATION = "Authorization";
    //redis token 前缀
    public static  final String REDIS_TOKEN_HEADER = "REDIS_TOKEN_HEADER_";

    //redis 内容过期时间  1day
    public static final long REDIS_CONTENT_EXPIRE_TIME = 1*24*60*60;
}
