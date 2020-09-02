package org.example.service.impl;

import org.example.service.OrderService;

public class OrderServiceImpl implements OrderService {

    @Override
    public boolean createOrder(int i) {
        System.out.println("create order:" + i);
        return true;
    }
}
