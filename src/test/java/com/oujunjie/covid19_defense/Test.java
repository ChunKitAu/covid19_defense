package com.oujunjie.covid19_defense;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.oujunjie.covid19_defense.covid.amap.entity.CommRet;
import com.oujunjie.covid19_defense.covid.amap.entity.LocaltionStatusRet;

import java.util.ArrayList;

/**
 * @auther ChunKitAu
 * @create 2021-03-28 28
 */
public class Test {


    @org.junit.Test
    public void Test(){
        String s = "{\"data\":{\"count\":32,\"results\":[{\"gfid\":23700,\"gfname\":\"fence_610100\",\"in\":0},{\"gfid\":22458,\"gfname\":\"fence_610000\",\"in\":1},{\"gfid\":18417,\"gfname\":\"fence_410000\",\"in\":0},{\"gfid\":21877,\"gfname\":\"fence_510100\",\"in\":0},{\"gfid\":23720,\"gfname\":\"fence_510000\",\"in\":0},{\"gfid\":23740,\"gfname\":\"fence_120000\",\"in\":0},{\"gfid\":21916,\"gfname\":\"fence_440100\",\"in\":0},{\"gfid\":17996,\"gfname\":\"fence_441200\",\"in\":0},{\"gfid\":19437,\"gfname\":\"fence_150000\",\"in\":0},{\"gfid\":18155,\"gfname\":\"fence_310000\",\"in\":0}]},\"errcode\":10000,\"errdetail\":null,\"errmsg\":\"OK\"}";

        CommRet commRet = JSONUtil.toBean(s, CommRet.class);
        System.out.println(commRet);
        LocaltionStatusRet statusRet = JSONUtil.toBean(commRet.getData(), LocaltionStatusRet.class);
        System.out.println(statusRet);


    }



    @org.junit.Test
    public void Test2(){
        JSONArray arr = new JSONArray();
        arr.add(1);
        arr.add(2);
        System.out.println(arr.toString());
        System.out.println(JSONUtil.toJsonStr(arr));
        JSONArray objects = JSONUtil.parseArray(arr.toString());
        System.out.println(objects.toString());
    }


    @org.junit.Test
    public void test3(){
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(10);
        add(arr);
        System.out.println(arr.toString());
    }


    public void add(ArrayList<Integer> arr){
        arr.add(1);
        arr.add(2);
        arr.add(3);
    }

    @org.junit.Test
    public void testMd5(){
        System.out.println(SecureUtil.md5().digestHex("123456"));
    }

}
