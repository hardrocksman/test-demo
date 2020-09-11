package org.example.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class MyMessageListener implements ChannelAwareMessageListener {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        MessageProperties properties = message.getMessageProperties();
        long tag = properties.getDeliveryTag();
        try {
            String correlationId = getCorrelationId(message);

            String messageStr = new String(message.getBody());

            Long msg = Long.parseLong(messageStr);

            log.info("message:[{}] take :[{}]ms", correlationId, System.currentTimeMillis() - msg);

            Map<String, Object> header = properties.getHeaders();
            Object preQueueName = header.get("");
            CorrelationData correlationData = new CorrelationData(correlationId);
            rabbitTemplate.convertAndSend("rabbit.test2.queue", (Object) messageStr, correlationData);

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
