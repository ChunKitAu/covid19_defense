package com.oujunjie.covid19_defense.covid.user.service;

import com.oujunjie.covid19_defense.comm.base.CommonResult;
import com.oujunjie.covid19_defense.covid.user.entity.po.User;
import org.springframework.web.multipart.MultipartFile;

/**
 * @auther ChunKitAu
 * @create 2021-03-28 28
 */
public interface UserService {

    CommonResult uploadUserByExcel(MultipartFile file);

    User getUserByOpenId(String openId);

    CommonResult BindUser(String openId, String uid);

}
