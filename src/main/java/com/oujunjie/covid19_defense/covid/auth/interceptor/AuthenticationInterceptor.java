package com.oujunjie.covid19_defense.covid.auth.interceptor;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.oujunjie.covid19_defense.comm.constans.UserConstans;
import com.oujunjie.covid19_defense.comm.my_exception.exceptions.AuthorizationException;
import com.oujunjie.covid19_defense.comm.utils.JWTUtils;
import com.oujunjie.covid19_defense.covid.auth.annotation.LoginRequired;
import com.oujunjie.covid19_defense.covid.auth.enums.RoleEnum;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @despriction
 * @auther ChunKitAu
 * @create 2020-03-04 04
 */
//@Configuration
public class AuthenticationInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        // 从 http 请求头中取出 token
        String token = httpServletRequest.getHeader(UserConstans.AUTHENTICATION);

        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有LoginRequired注释，没有有则跳过认证
        if (!method.isAnnotationPresent(LoginRequired.class)) {
            return true;
        }

        LoginRequired loginRequired = method.getAnnotation(LoginRequired.class);
        if (loginRequired.required()) {
            // 执行认证
            if (token == null) {
                throw  new AuthorizationException("无token，请重新登录");
            }
            DecodedJWT jwt = JWTUtils.verifierToken(token);

            RoleEnum role = loginRequired.role();
            if (role == RoleEnum.USER) {
                return true;
            }
            if (role == RoleEnum.ADMIN) {
                Long roleId = jwt.getClaim(JWTUtils.JWT_TOKEN_ROLE_ID).asLong();
                if (role != RoleEnum.getEnumTypeMap().get(roleId)) {
                    throw new AuthorizationException("token认证失败");
                }
            }

            return true;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }

}
