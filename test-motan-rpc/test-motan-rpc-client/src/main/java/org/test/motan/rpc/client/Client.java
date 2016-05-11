package org.test.motan.rpc.client;

import com.zhl.test.motan.rpc.IFooService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2016/5/11.
 */
public class Client {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:motan_client.xml");
        IFooService service = (IFooService) ctx.getBean("remoteService");
        System.out.println(service.hello("motan"));
    }
}
