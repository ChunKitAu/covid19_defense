package com.oujunjie.covid19_defense.covid.suspect.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Suspect {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 位置id
     */
    @Column(name = "position_id")
    private Long positionId;

    /**
     * 城市id
     */
    @Column(name = "city_id")
    private Long cityId;

    /**
     *  备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date ctime;


}