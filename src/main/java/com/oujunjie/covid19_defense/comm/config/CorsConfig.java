package com.oujunjie.covid19_defense.comm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @auther ChunKitAu
 * @create 2020-02-27 27
 */

@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        //1.添加CORS配置信息
        CorsConfiguration config = new CorsConfiguration();
        //放行哪些原始域
        config.addAllowedOrigin("*");
        //是否发送Cookie信息
//        config.setAllowCredentials(true);
        //放行哪些原始域(请求方式)
//        config.addAllowedMethod("GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
        config.addAllowedMethod("*");
        //放行哪些原始域(头部信息)
//        config.addAllowedHeader("X-Requested-With,Content-Type,Authorization,Origin,Accept");
        config.addAllowedHeader("*");
        //暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
        config.addExposedHeader("Authorization");
        //该响应的有效时间默认为30分钟，在有效时间内，浏览器无须为同一请求再次发起预检请求
        config.setMaxAge(1800L);
        //2.添加映射路径
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);

        //3.返回新的CorsFilter.
        return new CorsFilter(configSource);
    }
}