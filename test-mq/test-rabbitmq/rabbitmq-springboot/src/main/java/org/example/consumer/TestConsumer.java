//package org.example.consumer;
//
//import com.rabbitmq.client.Channel;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.core.MessageProperties;
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.amqp.rabbit.connection.CorrelationData;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//@Component
//@Slf4j
//public class TestConsumer {
//
//    @Autowired
//    RabbitTemplate rabbitTemplate;
//
//    @Value("${queue.defaultDeadQueue}")
//    private String defaultDeadQueue;
//
//    public static Map<String, String> QUEUE_CHAIN = new HashMap<String, String>();
//
////    @Value("${queue.test.dlq.queue}")
////    private String dlqQueue;
//
////    @RabbitHandler
////    @RabbitListener(queues = "${queue.test.dlq.queue}")
////    public void consume(Message message, Channel channel) throws IOException {
////        MessageProperties properties = message.getMessageProperties();
////        long tag = properties.getDeliveryTag();
////        try {
////            String correlationId = getCorrelationId(message);
////
////            String messageStr = new String(message.getBody());
////
////            Long msg = Long.parseLong(messageStr);
////
////            log.info("message:[{}] take :[{}]ms", correlationId, System.currentTimeMillis() - msg);
////
////            channel.basicAck(tag, false);
////        } catch (Exception e) {
////            log.error("process message error:[{}]", e);
////            channel.basicNack(tag, false, false);
////        }
////    }
//
//
////    @RabbitHandler
//    @RabbitListener(queues = "${queue.defaultDeadQueue}", autoStartup = "false", id = "settle-search-consumer")
//    public void consume(Message message, Channel channel) throws IOException {
//        MessageProperties properties = message.getMessageProperties();
//        long tag = properties.getDeliveryTag();
//
//        Map<String, Object> header = properties.getHeaders();
//        Object preQueueNameObj = header.get("x-first-death-queue");
//        String preQueueName = "";
//        if (preQueueNameObj != null) {
//            preQueueName = preQueueNameObj.toString();
//        }
//
//        try {
//            String correlationId = getCorrelationId(message);
//
//            String messageStr = new String(message.getBody());
//
//            Long msg = Long.parseLong(messageStr);
//
//            log.info("message:[{}] take :[{}]ms", correlationId, System.currentTimeMillis() - msg);
//
//            String nextQueue = QUEUE_CHAIN.get(preQueueName);
//            if (nextQueue == null) {
//                nextQueue = "test.1.queue";
//            }
//            if (!StringUtils.isEmpty(nextQueue)) {
//                CorrelationData correlationData = new CorrelationData(correlationId);
//                rabbitTemplate.convertAndSend(nextQueue, (Object) messageStr, correlationData);
//            }
//
//            channel.basicAck(tag, false);
//        } catch (Exception e) {
//            log.error("process message error:[{}]", e);
//            channel.basicNack(tag, false, false);
//        }
//    }
//
//    /**
//     * 获取CorrelationId
//     *
//     * @param message
//     * @return
//     */
//    private String getCorrelationId(Message message) {
//        String correlationId = null;
//
//        MessageProperties properties = message.getMessageProperties();
//        Map<String, Object> headers = properties.getHeaders();
//        for (Map.Entry entry : headers.entrySet()) {
//            String key = (String) entry.getKey();
//
//            if (key.equals("spring_returned_message_correlation")) {
//                String value = (String) entry.getValue();
//                correlationId = value;
//                return correlationId;
//            }
//        }
//
//        return correlationId;
//    }
//}
