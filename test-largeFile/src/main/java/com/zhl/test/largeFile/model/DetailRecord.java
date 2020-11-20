package com.zhl.test.largeFile.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;

@Data
public class DetailRecord {

    private String actArea;
    private String actNum;
    private String recAccNum;
    private String recAccName;
    private String recAccBank;
    private String tranDate;
    private String tranTime;
    private String valueDate;
    private String tranAmt;
    private String cDFlag;
    private String accBal;
    private String postTimestamp;
    private String billType;
    private String billNum;
    private String usage;
    private String abstr;
    private String payCode;
    private String comment;
    private String BusType;
    private String Originator;
    private String LowerLevelActNum;
    private String goodsName;
    private String benefitAmt;
    private String refundAmt;
    private String refundBenefitAmt;
    private String fee;
    private String feeRate;
    private String askNumber;
    private String uniqueID;
    private String CorpRefID;
    private String possCode;
    private String ReceiptNo;
    private String BusRef;
    private String BnkSeqNo;
    private String ActSeqNo;
    private String Mactibkn;
    private String Mactacn;
    private String Mactname;
    private String Mactbank;

    public static DetailRecord buildData() {
        DetailRecord record = new DetailRecord();
        record.setActArea("杭州市西湖区xxx支行");
        record.setActNum("1223234342343452342");
        record.setRecAccNum("287631287346428763");
        record.setRecAccBank("建设银行行长世行");
        record.setTranDate("2020-10-21");
        record.setTranTime("22:33:23");
        record.setValueDate("2020-01-22 19:23:22");
        record.setTranAmt("5433524345");
        record.setCDFlag("22");
        record.setAccBal("237546343564536");
        record.setPostTimestamp("323453423455342");
        record.setBillType("33");
        record.setBillNum("2332453543453452354");
        record.setUsage("什么用什么用什么事说什么话上世纪哈哈三个库打扫房间hi");
        record.setAbstr("什么用什么用什么事说什么话上世纪哈哈三个库打扫房间hi");
        record.setPayCode("897234873924428765");
        record.setComment("2176421沙路口附近撒即可是生产就sad");
        record.setBusType("43");
        record.setOriginator("243245325324");
        record.setLowerLevelActNum("32453245324");
        record.setGoodsName("223453425324534");
        record.setBenefitAmt("3243254324534fsfsdfsd");
        record.setRefundAmt("3243254324534fsfsdfsd");
        record.setRefundBenefitAmt("3243254324534fsfsdfsd");
        record.setFee("300.44");
        record.setFeeRate("0.33");
        record.setAskNumber("2335324534534");
        record.setUniqueID("2423452345324534");
        record.setCorpRefID("233245234532452345");
        record.setPossCode("xzsadsdasd24213321452");
        record.setReceiptNo("327682534jhkfsdjhgfskhfsjgd");
        record.setBusRef("23476sfjdkhsdfhab");
        record.setBnkSeqNo("273148784263sdacjh");
        record.setActSeqNo("sajkfbsdajh2389zxnm");
        record.setMactacn("241786kjfshascdabj");
        record.setMactibkn("234876ashkbjoijhsa");
        record.setMactname("jchsadjkcb21786hbjcsd");
        record.setMactbank("中国银行sasdsdf");
        return record;
    }
}
