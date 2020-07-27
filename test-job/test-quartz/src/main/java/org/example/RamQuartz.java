package org.example;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class RamQuartz {
    private static Logger _Logger = LoggerFactory.getLogger(RamQuartz.class);

    //设置任务启动时间
    static long applyLoanTime = System.currentTimeMillis() + 10*1000L;
    static long transferTime = System.currentTimeMillis() + 20*1000L;
    static long loanTime = System.currentTimeMillis() + 30*1000L;

    public static void main(String[] args) throws SchedulerException {
        //1.创建调度器工厂
        SchedulerFactory sFactory = new StdSchedulerFactory();
        //2.由调度器工厂创建调度器
        Scheduler scheduler = sFactory.getScheduler();
        //3.创建jobDetail对象，根据字节码对象匹配不同任务
        JobDetail applyJD = JobBuilder.newJob(RamJob.class)
                .withDescription("this is applyLoan task")
                .withIdentity("ramJob", "ramJobGroup").build();
        JobDetail transferJD = JobBuilder.newJob(RamJob2.class)
                .withDescription("this is transfer task")
                .withIdentity("ramJob2", "ramJob2Group").build();
        JobDetail loanJD = JobBuilder.newJob(RamJob3.class)
                .withDescription("this is loan task")
                .withIdentity("ramJob3", "ramJob3Group").build();
        //设置启动时间
        Date applyStartTime = new Date(applyLoanTime);
        Date transferStartTime = new Date(transferTime);
        Date loanStartTime = new Date(loanTime);

        //4.创建触发器对象
        Trigger t1 = TriggerBuilder.newTrigger()
                .withDescription("")
                .withIdentity("ramJobTrigger", "ramJobTriggerGroup")
                .startAt(applyStartTime)
                .withSchedule(CronScheduleBuilder.cronSchedule("0/1 * * * * ?")).build();
        Trigger t2 = TriggerBuilder.newTrigger()
                .withDescription("")
                .withIdentity("ramJob2Trigger", "ramJob2TriggerGroup")
                .startAt(transferStartTime)
                .withSchedule(CronScheduleBuilder.cronSchedule("0/1 * * * * ?")).build();
        Trigger t3 = TriggerBuilder.newTrigger()
                .withDescription("")
                .withIdentity("ramJob3Trigger", "ramJob3TriggerGroup")
                .startAt(loanStartTime)
                .withSchedule(CronScheduleBuilder.cronSchedule("0/1 * * * * ?")).build();

        //5.调度器注册
        scheduler.scheduleJob(applyJD, t1);
        scheduler.scheduleJob(transferJD, t2);
        scheduler.scheduleJob(loanJD, t3);

        //6.调度器执行
        scheduler.start();
        _Logger.info("开始执行调度器");
    }
}
