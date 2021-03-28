package com.oujunjie.covid19_defense.covid.amap.entity;

import lombok.Data;

/**
 * @auther ChunKitAu
 * @create 2021-03-28 28
 */
@Data
public class RetDistrict {

    String status;
    String info;
    String infocode;
    String count;
    DistrictDto[] districts;
}
