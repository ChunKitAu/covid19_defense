package com.oujunjie.covid19_defense.covid.amap.entity;

import lombok.Data;

/**
 * @auther ChunKitAu
 * @create 2021-03-28 28
 */
@Data
public class CommRet {
    Integer errcode;
    String errmsg;
    String errdetail;
    String data;
}
