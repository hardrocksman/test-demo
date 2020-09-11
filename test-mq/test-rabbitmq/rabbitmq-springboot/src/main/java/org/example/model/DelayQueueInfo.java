package org.example.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DelayQueueInfo {
    private String delayQueueName;
//    private String deadExchange;
//    private String deadRouteKey;
//    private String deadQueue;
    private Integer expireTime;
}
