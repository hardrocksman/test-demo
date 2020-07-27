package org.example;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RamJob implements Job {
    private static Logger _log = LoggerFactory.getLogger(RamJob.class);

    //伪代码 引入银行服务处理类，进行处理具体业务
    private static BankServiceImpl bsImpl = new BankServiceImpl();

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        _log.info("开始处理申请贷款业务");
        bsImpl.doApplyLoan();
        _log.info("申请贷款业务处理完成");
    }
}
