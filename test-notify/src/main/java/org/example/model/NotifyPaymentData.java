package org.example.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class NotifyPaymentData {
    private String noteCode;
    private String payDate;
    private String ourBankAccountNumber;
    private String ourBankAccountName;
    private String oppName;
    private String oppBankAccountNumber;
    private String oppBankAccountName;
    private String payState;
    private String paySendDate;
    private String payMadeDate;
    private String refundDate;
    private String srcBatchNo;
    private String srcSerialNo;
    private String srcNoteCode;
    private String payAmount;
    private String paidAmount;
}
