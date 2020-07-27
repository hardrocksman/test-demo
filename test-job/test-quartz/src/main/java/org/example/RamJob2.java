package org.example;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RamJob2 implements Job {
    private static Logger _log = LoggerFactory.getLogger(RamJob2.class);

    private static BankServiceImpl bsImpl = new BankServiceImpl();

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        _log.info("开始处理转账业务");
        bsImpl.doTransfer();
        _log.info("转账完成");
    }
}
