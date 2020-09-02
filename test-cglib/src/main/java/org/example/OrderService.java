package org.example;

public class OrderService {

    public boolean createOrder(int i) {
        System.out.println("create order:" + i);

        test();

        return true;
    }

    public void test () {
        System.out.println("test");
    }
}
