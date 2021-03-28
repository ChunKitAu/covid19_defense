package com.oujunjie.covid19_defense.comm.schedule;

import com.oujunjie.covid19_defense.covid.covid_data.service.CovidDataService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 * @auther ChunKitAu
 * @create 2021-03-27 27
 */

@Component
@Slf4j
public class SchedulerTask {

    private final Logger logger = LoggerFactory.getLogger(SchedulerTask.class);

    @Autowired
    CovidDataService covidDataService;

    // 每隔一个钟获取数据
//    @Scheduled(cron = "0 0 0/1 * * ?")
    @Scheduled(cron = "0 0/1 * * * ?")
//    @PostConstruct
    private void getCovData() {
        logger.info("get data from nCoV date=" + new Date());
        try {
            covidDataService.saveCovData();
        }catch (Exception  e){
            logger.error("Fail to get CovData");
            e.printStackTrace();
        }
    }
}
