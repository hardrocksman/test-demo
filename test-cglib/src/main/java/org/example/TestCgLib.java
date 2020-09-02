package org.example;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

public class TestCgLib {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(OrderService.class);
        enhancer.setCallback(new MyMethodInterceptor());
        OrderService orderService = (OrderService) enhancer.create();

        orderService.createOrder(1);

        orderService.test();
    }
}
