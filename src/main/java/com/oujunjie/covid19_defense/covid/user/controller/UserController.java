package com.oujunjie.covid19_defense.covid.user.controller;

import com.oujunjie.covid19_defense.comm.base.CommonResult;
import com.oujunjie.covid19_defense.covid.user.service.CheckInService;
import com.oujunjie.covid19_defense.covid.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @auther ChunKitAu
 * @create 2021-03-28 28
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    CheckInService checkInService;

    // excel 导入名单
    @PostMapping("/excel")
    public CommonResult uploadUserByExcel(@RequestParam("file") MultipartFile file) {
        userService.uploadUserByExcel(file);
        return CommonResult.success();
    }

    //todo 参数检验
    //检查当天是否签到
    @GetMapping("/isCheckIn")
    public CommonResult IsCheckIn(@RequestParam("openId") String openId) {
        System.out.println(openId);
        return checkInService.IsCheckInToday(openId);
    }

    //签到
    @PostMapping("/checkIn")
    public CommonResult CheckIn(@RequestParam("openId") String openId,
                                @RequestParam("x") double x,
                                @RequestParam("y") double y) {
        System.out.println(openId + x + y);
        return checkInService.CheckIn(openId, x, y);
    }

    @PostMapping("/bind")
    public CommonResult Bind(@RequestParam("openId") String openId,
                             @RequestParam("uid") String uid) {
        System.out.println(openId + uid);
        return userService.BindUser(openId, uid);
    }

}
