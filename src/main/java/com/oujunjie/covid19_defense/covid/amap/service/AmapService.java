package com.oujunjie.covid19_defense.covid.amap.service;

import java.util.ArrayList;

/**
 * @auther ChunKitAu
 * @create 2021-03-28 28
 */
public interface AmapService {

     void creatFence(ArrayList<Integer> locationIDs);

     void delAllFence();


     String isUserInFence(double x,double y);
}
