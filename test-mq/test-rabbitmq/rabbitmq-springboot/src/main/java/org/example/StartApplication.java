package org.example;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.example.model.DelayQueue;
import org.example.model.DelayQueueInfo;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableRabbit
@Slf4j
public class StartApplication {
    public static void main(String[] args) {

//        DelayQueueInfo delayQueueInfo1 = DelayQueueInfo.builder()
//                .delayQueueName("test.1.queue")
////                .deadExchange("test.1.exchange")
////                .deadRouteKey("test.1.routekey")
////                .deadQueue("test.dead.queue")
//                .expireTime(300000)
//                .build();
//
//        DelayQueueInfo delayQueueInfo2 = DelayQueueInfo.builder()
//                .delayQueueName("test.2.queue")
////                .deadExchange("test.2.exchange")
////                .deadRouteKey("test.2.routekey")
////                .deadQueue("test.dead.queue")
//                .expireTime(200000)
//                .build();
//        List<DelayQueueInfo> delayQueueInfoList = Arrays.asList(delayQueueInfo1, delayQueueInfo2);
//
//        DelayQueue delayQueue = DelayQueue.builder()
//                .defaultDeadExchange("test.default.dead.exchange")
//                .defaultDeadQueue("test.default.dead.queue")
//                .defaultDeadRouteKey("test.default.dead.routekey")
//                .delayQueues(delayQueueInfoList)
//                .build();
//
//        Gson gson = new Gson();
//        System.out.println(gson.toJson(delayQueue));

        SpringApplication sa  = new SpringApplication(StartApplication.class);
        sa.run(args);
    }
}
