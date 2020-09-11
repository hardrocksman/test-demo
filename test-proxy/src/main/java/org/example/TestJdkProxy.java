package org.example;

import org.example.handler.TxProxyHandler;
import org.example.service.OrderService;
import org.example.service.impl.OrderServiceImpl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Hello world!
 */
public class TestJdkProxy {
    public static void main(String[] args) {
        //1.设置saveGeneratedFiles属性为true，从而输出class
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        OrderServiceImpl orderServiceImpl = new OrderServiceImpl();
        OrderService orderService = (OrderService) Proxy.newProxyInstance(OrderService.class.getClassLoader(),
                new Class[]{OrderService.class},
                new TxProxyHandler(orderServiceImpl));
        orderService.createOrder(1);


//        try {
//            //2.获取代理类的class
//
//            Class proxyClazz = Proxy.getProxyClass(OrderService.class.getClassLoader(), OrderService.class);
//            //3.获得代理类的构造函数，并传入参数类型InvocationHandler.class
//            Constructor constructor = proxyClazz.getConstructor(InvocationHandler.class);
//            //4.通过构造函数来创建动态代理对象，将自定义的InvocationHandler实例传入
//            OrderService orderService = (OrderService) constructor.newInstance(new TxProxyHandler(new OrderServiceImpl()));
//            //5.通过代理对象调用目标方法
//            orderService.createOrder(2);
//
//            System.out.println("$Proxy0.class全名: " + proxyClazz);
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
