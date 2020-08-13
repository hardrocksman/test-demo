package org.example;

import java.lang.annotation.Target;
import java.lang.reflect.Method;

public class TestExtends extends Test {

    private Test target = new Test();

    @Override
    public void sayHello() {
        super.sayHello();
        System.out.println("hello at Test extends:");
    }

    @Override
    public void sayBye() {
        System.out.println("bye at Test extends:");
    }

    public void test(String name) {
        try {
            Method sayHelloMethod = Class.forName("org.example.Test").getDeclaredMethod("sayHello");
            sayHelloMethod.invoke(target, new Object[0]);
            System.out.println("hello at Test extends:" + name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
