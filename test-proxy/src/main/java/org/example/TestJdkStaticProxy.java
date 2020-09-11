package org.example;

import org.example.service.impl.OrderServiceImpl;
import org.example.service.impl.OrderServiceStaticProxy;


public class TestJdkStaticProxy {

    public static void main(String[] args) {
        OrderServiceImpl orderServiceImpl = new OrderServiceImpl();

        OrderServiceStaticProxy proxy = new OrderServiceStaticProxy(orderServiceImpl);

        proxy.createOrder(2);
    }
}
