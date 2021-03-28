package com.oujunjie.covid19_defense.covid.covid_data.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class City {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 城市名
     */
    private String name;

    /**
     * 城市编号
     */
    @Column(name = "location_id")
    private Integer locationId;

    /**
     * 确诊
     */
    private Integer confirm;

    /**
     * 当前确诊
     */
    @Column(name = "cur_confim")
    private Integer curConfim;

    /**
     * 死亡数
     */
    private Integer died;

    /**
     * 治愈数
     */
    private Integer heal;

    /**
     * 疑似
     */
    private Integer suspect;

    /**
     * 创建时间
     */
    private Date ctime;

}