package org.example;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RamJob3 implements Job {

    private static Logger _log = LoggerFactory.getLogger(RamJob3.class);

    private static BankServiceImpl bsImpl = new BankServiceImpl();

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        _log.info("开始处理放款业务");
        bsImpl.doLoan();
        _log.info("放款业务处理完成");

    }
}
