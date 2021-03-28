package com.oujunjie.covid19_defense.covid.amap.service.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.oujunjie.covid19_defense.comm.constans.RedisConstans;
import com.oujunjie.covid19_defense.comm.utils.RedisUtil;
import com.oujunjie.covid19_defense.covid.amap.config.AmapConfig;
import com.oujunjie.covid19_defense.covid.amap.entity.CommRet;
import com.oujunjie.covid19_defense.covid.amap.entity.DistrictFence;
import com.oujunjie.covid19_defense.covid.amap.entity.LocaltionStatus;
import com.oujunjie.covid19_defense.covid.amap.entity.LocaltionStatusRet;
import com.oujunjie.covid19_defense.covid.amap.service.AmapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @auther ChunKitAu
 * @create 2021-03-28 28
 */
@Service
public class AmapServiceImpl implements AmapService {

    private final Logger logger = LoggerFactory.getLogger(AmapServiceImpl.class);

    @Autowired
    RedisUtil redisUtil;

    @Override
    // 创建高德地图行政区围栏
    public void creatFence(ArrayList<Integer> locationIDs) {
        //先删除所有已存在的围栏
        this.delAllFence();

        logger.info("start createFence");
        locationIDs.forEach(val -> {
            try {
                Thread.sleep(300);
            }catch (Exception e){
                e.printStackTrace();
            }
            this.creatAmapDistinctFence(val);
        });

    }


    public void creatAmapDistinctFence(Integer locationID) {
        Map<String, Object> param = new HashMap<>();
        param.put("key", AmapConfig.key);
        param.put("sid", AmapConfig.sid);
        param.put("adcode", locationID);
        param.put("name", "fence_" + locationID);
        param.put("desc", "fence_" + locationID);

        String ret = HttpRequest.post("https://tsapi.amap.com/v1/track/geofence/add/district")
                .form(param)
                .execute().body();

        CommRet commRet = JSONUtil.toBean(ret, CommRet.class);
        logger.info("creatAmapDistrictFence = " + commRet.toString());
        if (commRet.getErrcode() != 10000) {
            logger.error(param.toString());
            return;
        }

        DistrictFence districtFence = JSONUtil.toBean(commRet.getData(), DistrictFence.class);

        if (redisUtil.hasKey(RedisConstans.FENCE_KEY)) {
            String redisData = (String) redisUtil.get(RedisConstans.FENCE_KEY);
            JSONArray fences = JSONUtil.parseArray(redisData);
            fences.add(districtFence.getGfid());
            redisUtil.set(RedisConstans.FENCE_KEY, fences.toString(), RedisConstans.TTL);
        } else {
            JSONArray arr = new JSONArray();
            arr.add(districtFence.getGfid());
            redisUtil.set(RedisConstans.FENCE_KEY, arr.toString(), RedisConstans.TTL);
        }
    }


    @Override//删除所有行政围栏
    public void delAllFence() {
        Map<String, Object> param = new HashMap<>();
        param.put("key", AmapConfig.key);
        param.put("sid", AmapConfig.sid);
        param.put("gfids", "#all");

        String ret = HttpRequest.post("https://tsapi.amap.com/v1/track/geofence/delete")
                .form(param)
                .execute().body();

        CommRet commRet = JSONUtil.toBean(ret, CommRet.class);
        logger.info("delAllFence ret = " + commRet.toString());
        if (commRet.getErrcode() == 0) {
            logger.info("delAllFence Success");
            redisUtil.del(RedisConstans.FENCE_KEY);
        } else logger.info("delAllFence Fail");

    }

    @Override
    public String isUserInFence(double x, double y) {
        int page = 1;
        int pageSize = 100;
        Map<String, Object> param = new HashMap<>();
        param.put("key", AmapConfig.key);
        param.put("sid", AmapConfig.sid);
        param.put("location", x + "," + y);
        param.put("page", page);
        param.put("pagesize", pageSize);

        while (true) {
            String ret = HttpUtil.get("https://tsapi.amap.com/v1/track/geofence/status/location", param);
            logger.info("isUserInFence ret= " + ret);
            CommRet commRet = JSONUtil.toBean(ret, CommRet.class);

            LocaltionStatusRet statusRet = JSONUtil.toBean(commRet.getData(), LocaltionStatusRet.class);
            LocaltionStatus[] results = statusRet.getResults();
            for (int i = 0; i < results.length; i++) {
                if (results[i].getIn() == 1) {
                    return results[i].getGfname();
                }
            }

            if (results.length < pageSize)
                break;

            page++;
            param.replace("page", page);
        }

        return null;
    }

}
