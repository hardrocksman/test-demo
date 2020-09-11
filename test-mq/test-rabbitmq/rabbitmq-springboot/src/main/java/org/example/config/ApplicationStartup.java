package org.example.config;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.example.consumer.TestConsumer;
import org.example.model.DelayQueueInfo;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {
    @Value("${queue.delay}")
    private String delayQueueStr;
    @Value("${queue.defaultDeadExchange}")
    private String defaultDeadExchange;
    @Value("${queue.defaultDeadRouteKey}")
    private String defaultDeadRouteKey;
    @Value("${queue.defaultDeadQueue}")
    private String defaultDeadQueue;
    @Autowired
    private RabbitAdmin rabbitAdmin;
    @Autowired
    private RabbitListenerEndpointRegistry rabbitListenerEndpointRegistry;
    @Autowired
    private TestConsumer consumer;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        // 初始化完成后. 执行一次同步系统参数
        MessageListenerContainer messageListenerContainer = rabbitListenerEndpointRegistry.getListenerContainer("settle-search-consumer");
        messageListenerContainer.stop();

        if (StringUtils.isEmpty(delayQueueStr)) {

        }
        Gson gson = new Gson();
        List<DelayQueueInfo> delayQueueInfoList = gson.fromJson(delayQueueStr, new TypeToken<List<DelayQueueInfo>>() {
        }.getType());

        delayQueueInfoList.sort(new Comparator<DelayQueueInfo>() {
            @Override
            public int compare(DelayQueueInfo o1, DelayQueueInfo o2) {
                return 0;
            }
        });

        log.info("开始配置死信队列....");
        QueueInformation deadQueueInformation = rabbitAdmin.getQueueInfo(defaultDeadQueue);
        if (deadQueueInformation == null) {
            // 死信队列不存在
            // 声明exchange
            DirectExchange directExchange = new DirectExchange(defaultDeadExchange);
            rabbitAdmin.declareExchange(directExchange);
            // 声明队列
            Queue deadQueue = new Queue(defaultDeadQueue, true, false, false);
            String re = rabbitAdmin.declareQueue(deadQueue);
            log.info("死信队列:[{}]创建结果:[{}]", defaultDeadQueue, re);
            // 声明绑定
            Binding deadQueueBind = BindingBuilder.bind(deadQueue).to(directExchange).with(defaultDeadRouteKey);
            rabbitAdmin.declareBinding(deadQueueBind);
        }

        log.info("死信队列配置成功...");
        log.info("开始配置延时队列...");
        for (int i = 0; i < delayQueueInfoList.size(); i++) {
            DelayQueueInfo delayQueueInfo = delayQueueInfoList.get(i);
            QueueInformation queueInformation = rabbitAdmin.getQueueInfo(delayQueueInfo.getDelayQueueName());

            if (queueInformation == null) {
                Map<String, Object> args = new HashMap<>(3);
                //声明死信交换器
                args.put("x-dead-letter-exchange", defaultDeadExchange);
                //声明死信路由键
                args.put("x-dead-letter-routing-key", defaultDeadRouteKey);
                //声明队列消息过期时间 30分钟
                args.put("x-message-ttl", delayQueueInfo.getExpireTime());
                //队列持久
                Queue q = new Queue(delayQueueInfo.getDelayQueueName(), true, false, false, args);
                String result = rabbitAdmin.declareQueue(q);
                log.info("延时队列:[{}]创建结果:[{}]", delayQueueInfo.getDelayQueueName(), result);
            }


            // 拿到下一个queuename
            if (i < delayQueueInfoList.size() - 1) {
                int nextIndex = i + 1;
                DelayQueueInfo nexDelayQueueInfo = delayQueueInfoList.get(nextIndex);
                if (nexDelayQueueInfo != null) {
                    TestConsumer.QUEUE_CHAIN.put(delayQueueInfo.getDelayQueueName(), nexDelayQueueInfo.getDelayQueueName());
                }
            }

            if (i == delayQueueInfoList.size() - 1) {
                TestConsumer.QUEUE_CHAIN.put(delayQueueInfo.getDelayQueueName(), "");
            }
        }

        if (TestConsumer.QUEUE_CHAIN.size() != delayQueueInfoList.size()) {
            log.info("延时队列链配置失败...");
            return;
        }

        log.info("延时队列配置成功...");

        log.info("延时队列配置成功...");
//        simpleMessageListenerContainer.addQueueNames(defaultDeadQueue);
//        simpleMessageListenerContainer.setMessageListener(myMessageListener);

        if (!messageListenerContainer.isRunning()) {
            messageListenerContainer.start();
        }
    }

}
