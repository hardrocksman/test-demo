package org.example.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
@Slf4j
public class TestConsumer {

//    @Autowired
//    RabbitTemplate rabbitTemplate;

//    @Value("${queue.test.dlq.queue}")
//    private String dlqQueue;

//    @RabbitHandler
//    @RabbitListener(queues = "${queue.test.dlq.queue}")
//    public void consume(Message message, Channel channel) throws IOException {
//        MessageProperties properties = message.getMessageProperties();
//        long tag = properties.getDeliveryTag();
//        try {
//            String correlationId = getCorrelationId(message);
//
//            String messageStr = new String(message.getBody());
//
//            Long msg = Long.parseLong(messageStr);
//
//            log.info("message:[{}] take :[{}]ms", correlationId, System.currentTimeMillis() - msg);
//
//            channel.basicAck(tag, false);
//        } catch (Exception e) {
//            log.error("process message error:[{}]", e);
//            channel.basicNack(tag, false, false);
//        }
//    }


    @RabbitHandler
    @RabbitListener(queues = "${queue.test.dlq.queue}")
    public void consume(Message message, Channel channel) throws IOException {
        MessageProperties properties = message.getMessageProperties();
        long tag = properties.getDeliveryTag();
        try {
            String correlationId = getCorrelationId(message);

            String messageStr = new String(message.getBody());

            Long msg = Long.parseLong(messageStr);

            log.info("message:[{}] take :[{}]ms", correlationId, System.currentTimeMillis() - msg);

            channel.basicAck(tag, false);
        } catch (Exception e) {
            log.error("process message error:[{}]", e);
            channel.basicNack(tag, false, false);
        }
    }

    /**
     * 获取CorrelationId
     *
     * @param message
     * @return
     */
    private String getCorrelationId(Message message) {
        String correlationId = null;

        MessageProperties properties = message.getMessageProperties();
        Map<String, Object> headers = properties.getHeaders();
        for (Map.Entry entry : headers.entrySet()) {
            String key = (String) entry.getKey();

            if (key.equals("spring_returned_message_correlation")) {
                String value = (String) entry.getValue();
                correlationId = value;
                return correlationId;
            }
        }

        return correlationId;
    }
}
