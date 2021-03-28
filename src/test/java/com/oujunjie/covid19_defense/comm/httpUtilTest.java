package com.oujunjie.covid19_defense.comm;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.oujunjie.covid19_defense.covid.covid_data.entity.dto.RetData;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;


/**
 * @auther ChunKitAu
 * @create 2021-03-27 27
 */
public class httpUtilTest {

    @Test
    public void sendGetRequest() {
        Map<String,Object> param = new HashMap<>();
        param.put("latest",1);
        param.put("province","湖北省");

//        String ret = HttpUtil.get("https://lab.isaaclin.cn/nCoV/api/area",param);
        String ret ="{\"results\":[{\"locationId\":420000,\"continentName\":\"亚洲\",\"continentEnglishName\":\"Asia\",\"countryName\":\"中国\",\"countryEnglishName\":\"China\",\"countryFullName\":null,\"provinceName\":\"湖北省\",\"provinceEnglishName\":\"Hubei\",\"provinceShortName\":\"湖北\",\"currentConfirmedCount\":1,\"confirmedCount\":68152,\"suspectedCount\":0,\"curedCount\":63639,\"deadCount\":4512,\"comment\":\"4.17武汉市订正数据，武汉死亡核增1290，确诊核增325，治愈核减965\",\"cities\":[{\"cityName\":\"武汉\",\"currentConfirmedCount\":1,\"confirmedCount\":50357,\"suspectedCount\":0,\"curedCount\":46487,\"deadCount\":3869,\"highDangerCount\":0,\"midDangerCount\":0,\"locationId\":420100,\"currentConfirmedCountStr\":\"1\",\"cityEnglishName\":\"Wuhan\"},{\"cityName\":\"孝感\",\"currentConfirmedCount\":0,\"confirmedCount\":3518,\"suspectedCount\":0,\"curedCount\":3389,\"deadCount\":129,\"highDangerCount\":0,\"midDangerCount\":0,\"locationId\":420900,\"currentConfirmedCountStr\":\"0\",\"cityEnglishName\":\"Xiaogan\"},{\"cityName\":\"黄冈\",\"currentConfirmedCount\":0,\"confirmedCount\":2907,\"suspectedCount\":0,\"curedCount\":2782,\"deadCount\":125,\"highDangerCount\":0,\"midDangerCount\":0,\"locationId\":421100,\"currentConfirmedCountStr\":\"0\",\"cityEnglishName\":\"Huanggang\"},{\"cityName\":\"荆州\",\"currentConfirmedCount\":0,\"confirmedCount\":1580,\"suspectedCount\":0,\"curedCount\":1528,\"deadCount\":52,\"highDangerCount\":0,\"midDangerCount\":0,\"locationId\":421000,\"currentConfirmedCountStr\":\"0\",\"cityEnglishName\":\"Jingzhou\"},{\"cityName\":\"鄂州\",\"currentConfirmedCount\":0,\"confirmedCount\":1394,\"suspectedCount\":0,\"curedCount\":1335,\"deadCount\":59,\"highDangerCount\":0,\"midDangerCount\":0,\"locationId\":420700,\"currentConfirmedCountStr\":\"0\",\"cityEnglishName\":\"Ezhou\"},{\"cityName\":\"随州\",\"currentConfirmedCount\":0,\"confirmedCount\":1307,\"suspectedCount\":0,\"curedCount\":1262,\"deadCount\":45,\"highDangerCount\":0,\"midDangerCount\":0,\"locationId\":421300,\"currentConfirmedCountStr\":\"0\",\"cityEnglishName\":\"Suizhou\"},{\"cityName\":\"襄阳\",\"currentConfirmedCount\":0,\"confirmedCount\":1175,\"suspectedCount\":0,\"curedCount\":1135,\"deadCount\":40,\"highDangerCount\":0,\"midDangerCount\":0,\"locationId\":420600,\"currentConfirmedCountStr\":\"0\",\"cityEnglishName\":\"Xiangyang\"},{\"cityName\":\"黄石\",\"currentConfirmedCount\":0,\"confirmedCount\":1015,\"suspectedCount\":0,\"curedCount\":976,\"deadCount\":39,\"highDangerCount\":0,\"midDangerCount\":0,\"locationId\":420200,\"currentConfirmedCountStr\":\"0\",\"cityEnglishName\":\"Huangshi\"},{\"cityName\":\"宜昌\",\"currentConfirmedCount\":0,\"confirmedCount\":931,\"suspectedCount\":0,\"curedCount\":894,\"deadCount\":37,\"highDangerCount\":0,\"midDangerCount\":0,\"locationId\":420500,\"currentConfirmedCountStr\":\"0\",\"cityEnglishName\":\"Yichang\"},{\"cityName\":\"荆门\",\"currentConfirmedCount\":0,\"confirmedCount\":928,\"suspectedCount\":0,\"curedCount\":887,\"deadCount\":41,\"highDangerCount\":0,\"midDangerCount\":0,\"locationId\":420800,\"currentConfirmedCountStr\":\"0\",\"cityEnglishName\":\"Jingmen\"},{\"cityName\":\"咸宁\",\"currentConfirmedCount\":0,\"confirmedCount\":836,\"suspectedCount\":0,\"curedCount\":821,\"deadCount\":15,\"highDangerCount\":0,\"midDangerCount\":0,\"locationId\":421200,\"currentConfirmedCountStr\":\"0\",\"cityEnglishName\":\"Xianning\"},{\"cityName\":\"十堰\",\"currentConfirmedCount\":0,\"confirmedCount\":672,\"suspectedCount\":0,\"curedCount\":664,\"deadCount\":8,\"highDangerCount\":0,\"midDangerCount\":0,\"locationId\":420300,\"currentConfirmedCountStr\":\"0\",\"cityEnglishName\":\"Shiyan\"},{\"cityName\":\"仙桃\",\"currentConfirmedCount\":0,\"confirmedCount\":575,\"suspectedCount\":0,\"curedCount\":553,\"deadCount\":22,\"highDangerCount\":0,\"midDangerCount\":0,\"locationId\":429004,\"currentConfirmedCountStr\":\"0\",\"cityEnglishName\":\"Xiantao\"},{\"cityName\":\"天门\",\"currentConfirmedCount\":0,\"confirmedCount\":496,\"suspectedCount\":0,\"curedCount\":481,\"deadCount\":15,\"highDangerCount\":0,\"midDangerCount\":0,\"locationId\":429006,\"currentConfirmedCountStr\":\"0\",\"cityEnglishName\":\"Tianmen\"},{\"cityName\":\"恩施州\",\"currentConfirmedCount\":0,\"confirmedCount\":252,\"suspectedCount\":0,\"curedCount\":245,\"deadCount\":7,\"highDangerCount\":0,\"midDangerCount\":0,\"locationId\":422800,\"currentConfirmedCountStr\":\"0\",\"cityEnglishName\":\"Enshi Tujia and Miao Autonomous Prefecture\"},{\"cityName\":\"潜江\",\"currentConfirmedCount\":0,\"confirmedCount\":198,\"suspectedCount\":0,\"curedCount\":189,\"deadCount\":9,\"highDangerCount\":0,\"midDangerCount\":0,\"locationId\":429005,\"currentConfirmedCountStr\":\"0\",\"cityEnglishName\":\"Qianjiang\"},{\"cityName\":\"神农架林区\",\"currentConfirmedCount\":0,\"confirmedCount\":11,\"suspectedCount\":0,\"curedCount\":11,\"deadCount\":0,\"highDangerCount\":0,\"midDangerCount\":0,\"locationId\":429021,\"currentConfirmedCountStr\":\"0\",\"cityEnglishName\":\"Shennongjia\"}],\"updateTime\":1616634133012}],\"success\":true}";
        System.out.println(ret);

        RetData data = JSONUtil.toBean(ret,RetData.class);
        System.out.println(data);
    }
}