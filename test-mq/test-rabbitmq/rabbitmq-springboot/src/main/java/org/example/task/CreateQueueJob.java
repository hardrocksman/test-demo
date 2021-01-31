package org.example.task;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;

public class CreateQueueJob implements Runnable{

    private RabbitAdmin rabbitAdmin;

    public CreateQueueJob(RabbitAdmin rabbitAdmin) {
        this.rabbitAdmin = rabbitAdmin;
    }

    @Override
    public void run() {
        Queue deadQueue = new Queue("test-1-1-1", true, false, false);
        String re = rabbitAdmin.declareQueue(deadQueue);

        System.out.println("线程：" + Thread.currentThread().getId() + ",返回结果：" + re);
    }
}
