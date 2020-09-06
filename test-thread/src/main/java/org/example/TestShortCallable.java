package org.example;

import java.util.concurrent.Callable;

public class TestShortCallable implements Callable<Boolean> {

    private int index;

    public TestShortCallable(int index) {
        this.index = index;
    }

    @Override
    public java.lang.Boolean call() throws Exception {
        System.out.println("thread:" + Thread.currentThread().getName() + " process:" + index);
        Thread.sleep(2000);
        return true;
    }
}
