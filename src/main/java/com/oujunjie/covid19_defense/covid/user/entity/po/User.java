package com.oujunjie.covid19_defense.covid.user.entity.po;

import javax.persistence.*;

@Table(name = "cuser")
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
     * 身份
     */
    private Integer role;

}