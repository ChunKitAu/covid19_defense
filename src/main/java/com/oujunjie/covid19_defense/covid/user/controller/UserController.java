package com.oujunjie.covid19_defense.covid.user.controller;

import com.oujunjie.covid19_defense.comm.base.CommonResult;
import com.oujunjie.covid19_defense.covid.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @auther ChunKitAu
 * @create 2021-03-28 28
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/excel")
    public CommonResult uploadUserByExcel(@RequestParam("file") MultipartFile file){
            userService.uploadUserByExcel(file);
            return CommonResult.success();
    }

}
