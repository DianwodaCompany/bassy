package com.dianwoda.test.bassy.service.task;

import com.dianwoda.test.bassy.service.StatisticsService;
import com.dianwoda.test.bassy.service.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

/**
 * @author zcp
 * 2019/12/20 下午10:54
 * Most is the gentleness which that one lowers the head,
 * looks like a water lotus flower extremely cool breeze charming.
 */
@Component
public class GenWorkDailyReports {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StatisticsService statisticsService;

    public void doExecute() throws Throwable {
        logger.info("\n触发生成日常日报:定时任务,当前时间为：{}开始触发{}任务……",new Date(),"生成小组日报");
        Date date = DateUtil.add(new Date(), Calendar.DATE, -1);
        statisticsService.genWorkTomorrow(DateUtil.toLocaleString(date, "yyyy-MM-dd"));
        statisticsService.genWorkTodayAct(DateUtil.toLocaleString(date, "yyyy-MM-dd"));
        logger.info("\n触发生成日常日报:定时任务,当前时间为：{}结束触发{}任务……",new Date(),"生成小组日报");

    }
}
