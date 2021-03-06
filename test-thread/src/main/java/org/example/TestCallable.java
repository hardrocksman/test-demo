package org.example;

import java.util.concurrent.Callable;

public class TestCallable implements Callable<java.lang.Boolean> {

    private int index;

    public TestCallable(int index) {
        this.index = index;
    }

    @Override
    public java.lang.Boolean call() throws Exception {
        System.out.println("thread:" + Thread.currentThread().getName() + " process:" + index);
        Thread.sleep(1000000);
        return true;
    }
}
