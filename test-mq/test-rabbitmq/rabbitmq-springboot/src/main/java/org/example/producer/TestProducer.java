package org.example.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class TestProducer {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Value("${queue.test.name}")
    private String testQueue;

    @PostConstruct
    public void run() {
//        Thread t = new Thread(new SendJob(testQueue, rabbitTemplate));
//        t.start();
    }
}

class SendJob implements Runnable {

    private String queue;
    private RabbitTemplate rabbitTemplate;

    public SendJob(String queue, RabbitTemplate rabbitTemplate) {
        this.queue = queue;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {

            Long now = System.currentTimeMillis();

            CorrelationData correlationData = new CorrelationData(now.toString());
            rabbitTemplate.convertAndSend("test.1.queue", (Object) now.toString(), correlationData);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

