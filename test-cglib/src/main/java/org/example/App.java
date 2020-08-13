package org.example;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
//        System.out.println( "Hello World!" );

        TestExtends testExtends = new TestExtends();
        testExtends.test("hard");


        // 代理类class文件存入本地磁盘方便我们反编译查看源码
//        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "E:\\read_src\\test-demo\\test-cglib\\src\\source");
        // 通过CGLIB动态代理获取代理对象的过程
//        Enhancer enhancer = new Enhancer();
//        // 设置enhancer对象的父类
//        enhancer.setSuperclass(HelloService.class);
//        // 设置enhancer的回调对象
//        enhancer.setCallback(new MyMethodInterceptor());
//        // 创建代理对象
//        HelloService proxy= (HelloService)enhancer.create();
//        // 通过代理对象调用目标方法
//        proxy.sayHello();

//        ((HelloService) new CglibProxyA().getProxyInstance(new HelloService())).sayHello();
//        ((HelloService) new CglibProxyB().getProxyInstance(new HelloService())).sayHello();
    }
}
