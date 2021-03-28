package com.oujunjie.covid19_defense.covid.covid_data.entity.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @auther ChunKitAu
 * @create 2021-03-27 27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class RetData {
    RetArea[] results ;
}
