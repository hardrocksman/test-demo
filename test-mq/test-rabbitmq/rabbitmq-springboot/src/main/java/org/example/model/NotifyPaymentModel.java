package org.example.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotifyPaymentModel {

    /**
     * 回调地址
     */
    private String notifyUrl;
    /**
     * 回调次数
     */
    private Integer notifyCount;
    /**
     * 回调数据
     */
    private String data;
}

