package com.oujunjie.covid19_defense.comm.base;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Data
@Accessors(chain = true)
@Slf4j
public class CommonResult {
    private final static int SUCCESS_CODE = 200;
    private final static int FAILURE_CODE = 500;
    private final static String SUCCESS_STRING = "请求成功";
    private final static String FAILURE_STRING = "请求失败";

    private int ret;
    private String msg;
    private Object data;

    public static CommonResult success(Object data) {
        return success().setData(data);
    }

    public static CommonResult success() {
        return new CommonResult()
                .setRet(SUCCESS_CODE)
                .setMsg(SUCCESS_STRING);
    }

    public static CommonResult failure(String message) {
        return failure().setMsg(message);
    }

    public static CommonResult failure() {
        return new CommonResult()
                .setRet(FAILURE_CODE)
                .setMsg(FAILURE_STRING);
    }

}
