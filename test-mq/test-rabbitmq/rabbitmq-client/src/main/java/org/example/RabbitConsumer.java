package org.example;

import com.rabbitmq.client.*;

import java.io.IOException;

public class RabbitConsumer {

    public static void main(String[] args) {
        try {
            //创建连接工厂对象
            ConnectionFactory factory = new ConnectionFactory();
            //设置工厂对象的参数，用来连接rabbitmq
             factory.setHost("localhost");
            //建立程序与rabbitmq的socket连接
             Connection connection = factory.newConnection();
            //创建管道
            final Channel channel = connection.createChannel();
            //声明队列
            channel.queueDeclare("test.rabbit.queue", false, false, false, null);
            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
            
            //创建队列消费对象
            Consumer consumer = new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
                    String message = new String(body);
                    System.out.println("Received: " + message);
                    // 消息确认
                    try {
                        channel.basicAck(envelope.getDeliveryTag(), false);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            //设置参数
            channel.basicConsume("test.rabbit.queue", true, consumer);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
