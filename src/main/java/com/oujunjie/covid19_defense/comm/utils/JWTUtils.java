package com.oujunjie.covid19_defense.comm.utils;

import cn.hutool.core.util.RandomUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.oujunjie.covid19_defense.comm.constans.UserConstans;
import com.oujunjie.covid19_defense.comm.my_exception.exceptions.AuthorizationException;
import com.oujunjie.covid19_defense.covid.user.entity.po.User;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT 工具类
 *
 * @auther ChunKit.Ou
 * @data 11/3/2020 2:48 PM
 **/
public class JWTUtils {


    //私钥
    private static final String TOKEN_SECRET = "OuJunJie_Key";

    //载体中用户信息的key
    public static final String JWT_TOKEN_ID = "jwt_token_id";

    public static final String JWT_TOKEN_ROLE_ID = "jwt_token_role_id";

    //过期时间  1day  单位为 秒
    public static final Integer DEFAULT_EXPIRES_TIME = 24 * 3600;


    /**
     * 生成密令
     *
     * @param user 存储的信息
     * @return
     */
    public static String getToken(User user) {
        // 设置头部信息
        Map<String, Object> header = new HashMap<>(2);
        header.put("Type", "Jwt");
        header.put("alg", "HS256");

        //过期功能由redis实现
        String token = JWT.create()
                .withHeader(header) //头部信息
                .withClaim(JWT_TOKEN_ID, user.getId())
                .withClaim(JWT_TOKEN_ROLE_ID, user.getRole())
                .withIssuedAt(new Date())   // 生成签名的时间
                .withNotBefore(new Date())  //生效时间
                .sign(Algorithm.HMAC256(TOKEN_SECRET));
        String[] split = token.split("\\.");

        //在playLoad 中加入字符干扰 jwt解析
        return split[0] + "." + RandomUtil.randomString(1) + split[1] + RandomUtil.randomString(1) + "." + split[2];
    }


    /**
     * 验证token
     *
     * @param token
     * @return
     */
    public static DecodedJWT verifierToken(String token) {
        //先过滤干扰字符
        String[] split = token.split("\\.");
        token = split[0] + "." + split[1].substring(1, split[1].length() - 1) + "." + split[2];
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).build();
            DecodedJWT verify = verifier.verify(token);

            Long userId = verify.getClaim(JWT_TOKEN_ID).asLong();
            RedisUtil redisUtil = BeanTool.getBean(RedisUtil.class);
            String reids_token = (String) redisUtil.get(UserConstans.REDIS_TOKEN_HEADER + userId);
            if (StringUtils.isEmpty(reids_token)) {
                throw new AuthorizationException("无效Token");
            }

            return verify;
        } catch (TokenExpiredException e) {
            throw new AuthorizationException("令牌过期！");
        } catch (JWTVerificationException e) {
            throw new AuthorizationException("Token解析失败");
        }
    }


}
