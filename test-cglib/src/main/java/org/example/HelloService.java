package org.example;

public class HelloService {

    public void sayHello() {
        this.sayBye();
        System.out.println("HelloService:sayHello");
    }

    public void sayBye() {
        System.out.println("HelloService:sayBye");
    }
}
