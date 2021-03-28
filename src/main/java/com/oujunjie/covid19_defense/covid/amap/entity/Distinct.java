package com.oujunjie.covid19_defense.covid.amap.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @auther ChunKitAu
 * @create 2021-03-28 28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Distinct {
    Float[] x;
    Float[] y;
    int len;

}
