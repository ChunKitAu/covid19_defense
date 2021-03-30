package com.oujunjie.covid19_defense.comm.utils;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.interfaces.DecodedJWT;

import com.oujunjie.covid19_defense.comm.constans.UserConstans;
import com.oujunjie.covid19_defense.comm.my_exception.exceptions.AuthorizationException;
import com.oujunjie.covid19_defense.covid.user.dao.UserDao;
import com.oujunjie.covid19_defense.covid.user.entity.po.User;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @despriction 用于验证token
 * @auther ChunKitAu
 * @create 2020-03-04 04
 */
public class SessionUtil {

    public static User getUserSeiionInfo() {
        // 获取请求对象
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取请求头的Token值
        String token = request.getHeader(UserConstans.AUTHENTICATION);

        if (StrUtil.hasBlank(token)) {
            return null;
        }

        //解析 验证token 获取userId
        DecodedJWT jwt = JWTUtils.verifierToken(token);
        Long userId = jwt.getClaim(JWTUtils.JWT_TOKEN_ID).asLong();

        RedisUtil redisUtil = BeanTool.getBean(RedisUtil.class);
        String reids_token = (String) redisUtil.get(UserConstans.REDIS_TOKEN_HEADER + userId);

        if (StringUtils.isEmpty(reids_token)) {
            throw new AuthorizationException("无效Token");
        }

        UserDao userDao = BeanTool.getBean(UserDao.class);
        User user = userDao.selectByPrimaryKey(userId);
        if (user == null) {
            throw new AuthorizationException("该用户不存在，请重新登陆");
        }


        return new User();
    }

}
