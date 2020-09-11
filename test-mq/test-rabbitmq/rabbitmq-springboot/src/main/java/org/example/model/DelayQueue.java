package org.example.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DelayQueue {

    private String defaultDeadExchange;
    private String defaultDeadRouteKey;
    private String defaultDeadQueue;

    private List<DelayQueueInfo> delayQueues;
}
