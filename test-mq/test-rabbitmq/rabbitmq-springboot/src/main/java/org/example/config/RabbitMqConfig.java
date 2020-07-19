package org.example.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
public class RabbitMqConfig {

    @Autowired
    private CachingConnectionFactory connectionFactory;
    @Value("${queue.test.name}")
    private String queueName;

    @Value("${queue.test.dlx.exchange}")
    private String dlxExchange;

    @Value("${queue.test.dlq.queue}")
    private String dlqQueue;

    @Value("${queue.test.dlx.routeKey}")
    private String dlxRouteKey;

    /**
     * 为队列绑定死信队列
     * @return
     */
    @Bean
    public Queue queueA() {
        Map<String, Object> args = new HashMap<>(3);
        //声明死信交换器
        args.put("x-dead-letter-exchange", dlxExchange);
        //声明死信路由键
        args.put("x-dead-letter-routing-key", dlxRouteKey);
        //声明队列消息过期时间 30分钟
        args.put("x-message-ttl", 300000);
        //队列持久
        return new Queue(queueName, true, false, false, args);
    }

    /**
     * 死信队列的exchange
     */
    @Bean
    public DirectExchange dlxExchange() {
        return new DirectExchange(dlxExchange);
    }

    /**
     * 死信队列
     * @return
     */
    @Bean
    public Queue dlqQueue() {
        return new Queue(dlqQueue, true);//队列持久
    }

    @Bean
    public Binding bindingDlQueue() {
        return BindingBuilder.bind(dlqQueue()).to(dlxExchange()).with(dlxRouteKey);
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//        rabbitTemplate.setMessageConverter(converter());

        // 消息是否成功发送到Exchange

        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                log.info("消息成功发送到Exchange");
                String msgId = correlationData.getId();
//                msgLogService.updateStatus(msgId, Constant.MsgLogStatus.DELIVER_SUCCESS);
                log.info("message :[{}] send success", msgId);
            } else {
                log.info("消息发送到Exchange失败, {}, cause: {}", correlationData, cause);
            }
        });

        // 触发setReturnCallback回调必须设置mandatory=true, 否则Exchange没有找到Queue就会丢弃掉消息, 而不会触发回调
        rabbitTemplate.setMandatory(true);
        // 消息是否从Exchange路由到Queue, 注意: 这是一个失败回调, 只有消息从Exchange路由到Queue失败才会回调这个方法
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            log.info("消息从Exchange路由到Queue失败: exchange: {}, route: {}, replyCode: {}, replyText: {}, message: {}", exchange, routingKey, replyCode, replyText, message);
        });

        return rabbitTemplate;
    }
}
