package com.dianwoda.test.bassy.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author zcp
 * 2019/12/20 下午10:59
 * Most is the gentleness which that one lowers the head,
 * looks like a water lotus flower extremely cool breeze charming.
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}, scanBasePackages="com.dianwoda.test.bassy")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
