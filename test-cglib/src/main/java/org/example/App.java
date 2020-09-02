package org.example;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;

import java.io.File;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        // 代理类class文件存入本地磁盘方便我们反编译查看源码
        String path = System.getProperty("user.dir") + "\\test-cglib\\source";
        System.out.println(path);
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, path);
        // 通过CGLIB动态代理获取代理对象的过程

        ((OrderService) new CglibProxyB().getProxyInstance(new OrderService())).createOrder(2);
        ((OrderService) new CglibProxyB().getProxyInstance(new OrderService())).test();
    }
}
