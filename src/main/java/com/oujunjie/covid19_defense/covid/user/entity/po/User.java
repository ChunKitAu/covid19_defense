package com.oujunjie.covid19_defense.covid.user.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class User {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 工号/学号
     */
    private String uid;

    /**
     * 微信open_id
     */
    @Column(name = "open_id")
    private String openId;

    /**
     * 密码
     */
    private String password;

    /**
     * 学院
     */
    private String college;

    /**
     * 年级
     */
    private String grade;

    /**
     * 身份
     */
    private Integer role;

    /**
     * 创建时间
     */
    private Date ctime;

    /**
     * 修改时间
     */
    private Date mtime;
}