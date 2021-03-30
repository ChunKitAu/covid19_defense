package com.oujunjie.covid19_defense.comm.my_exception.hander;

import com.oujunjie.covid19_defense.comm.base.CommonResult;
import com.oujunjie.covid19_defense.comm.my_exception.exceptions.AuthorizationException;
import com.oujunjie.covid19_defense.comm.my_exception.exceptions.POIException;
import com.oujunjie.covid19_defense.comm.my_exception.exceptions.UserException;
import com.oujunjie.covid19_defense.comm.my_exception.exceptions.UserNotBindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @auther ChunKitAu
 * @create 2021-03-28 28
 */

@ControllerAdvice
public class MyControllerAdvice {

    @ResponseBody
    @ExceptionHandler(value = POIException.class)
    public CommonResult myErrorHandler(POIException ex) {
        return CommonResult.failure(ex.getMessage()).setRet(5001001);
    }

    @ResponseBody
    @ExceptionHandler(value = UserException.class)
    public CommonResult myErrorHandler(UserException ex) {
        return CommonResult.failure(ex.getMessage()).setRet(5001002);
    }

    @ResponseBody
    @ExceptionHandler(value = UserNotBindException.class)
    public CommonResult myErrorHandler(UserNotBindException ex) {
        return CommonResult.failure(ex.getMessage()).setRet(5001003);
    }

    @ResponseBody
    @ExceptionHandler(value = AuthorizationException.class)
    public CommonResult myErrorHandler(AuthorizationException ex) {
        return CommonResult.failure(ex.getMessage()).setRet(5001004);
    }

}
