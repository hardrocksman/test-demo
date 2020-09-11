package org.example.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TxProxyHandler implements InvocationHandler {

    private Object target;

    public TxProxyHandler(Object target) {
        //初始化代理对象
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("处理前");
        Object result = method.invoke(target, args);
        System.out.println("处理后");
        return result;
    }
}
