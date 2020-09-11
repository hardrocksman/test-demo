package org.example.service.impl;

import org.example.service.OrderService;

public class OrderServiceStaticProxy implements OrderService {

    private OrderServiceImpl orderServiceImpl;

    public OrderServiceStaticProxy(OrderServiceImpl orderServiceImpl) {
        this.orderServiceImpl = orderServiceImpl;
    }

    @Override
    public boolean createOrder(int i) {
        System.out.println("--------------执行前 ---------------");

        boolean result = orderServiceImpl.createOrder(i);

        System.out.println("--------------执行后 ---------------");

        return result;
    }
}
