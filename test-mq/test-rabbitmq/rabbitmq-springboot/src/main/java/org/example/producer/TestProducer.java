package org.example.producer;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.example.model.NotifyPaymentModel;
import org.example.model.PanelData;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Component
@Slf4j
public class TestProducer {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Value("${queue.test.notify}")
    private String testQueue;

    @PostConstruct
    public void run() {
        Thread t = new Thread(new SendJob("settle.notify.queue", rabbitTemplate));
        t.start();
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
        for (int i = 0; i < 2; i++) {
            Gson gson = new Gson();

            Long now = System.currentTimeMillis();
            String uuid = UUID.randomUUID().toString();
//            PanelData pa = PanelData.builder()
//                    .GunNumber("GunNumber")
//                    .holdDeptId(11L)
//                    .uuid(uuid)
//                    .reason(0)
//                    .timestamp(System.currentTimeMillis())
//                    .build();

            NotifyPaymentModel model = NotifyPaymentModel.builder()
                    .data("hgjkaskhgbjdkjhsbdskjhda")
                    .notifyCount(0)
                    .notifyUrl("http://aaa.sss")
                    .build();

            String sendData = gson.toJson(model);

            System.out.println("send 发送到panel mq data:[{}]" + sendData);

            CorrelationData correlationData = new CorrelationData(uuid);
            rabbitTemplate.convertAndSend(queue, (Object) sendData, correlationData);

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

