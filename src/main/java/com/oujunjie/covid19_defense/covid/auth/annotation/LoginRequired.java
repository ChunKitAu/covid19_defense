package com.oujunjie.covid19_defense.covid.auth.annotation;


import com.oujunjie.covid19_defense.covid.auth.enums.RoleEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @despriction 用于jwt登陆验证
 * @auther ChunKitAu
 * @create 2020-02-27 27
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginRequired {

    boolean required() default true;

    //默认为用户
    RoleEnum role() default RoleEnum.USER;
}
