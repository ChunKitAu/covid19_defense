package com.oujunjie.covid19_defense;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;



@MapperScan("com.oujunjie.covid19_defense.covid.*.dao")
@EnableScheduling
@EnableAsync
@SpringBootApplication
public class Covid19DefenseApplication {

    public static void main(String[] args) {
        SpringApplication.run(Covid19DefenseApplication.class, args);
    }

}
