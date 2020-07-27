package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BankServiceImpl {
    private static Logger _log = LoggerFactory.getLogger(BankServiceImpl.class);

    public void doLoan(){
        System.out.println("银行开始放款");
        _log.info("银行开始放款");
    }

    public void doTransfer(){
        System.out.println("借款人A转帐1000给借款人B");
        _log.info("借款人A转帐1000给借款人B");
    }

    public void doApplyLoan(){
        System.out.println("申请人申请贷款");
        _log.info("申请人申请贷款");
    }
}
