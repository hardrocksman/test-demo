package org.example;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class AgentApp
{
    public static void main( String[] args ) throws InterruptedException {

        // -javaagent:D:\work\read_src\test-demo\test-agent\agent-core\target\agent-core-1.0-SNAPSHOT.jar=sdhjsdhdshj
        System.out.println( "Hello World! 我是应用程序。。。。。" );

        System.out.println("按数字键 1 调用测试方法");
        while (true) {
            Scanner reader = new Scanner(System.in);
            int number = reader.nextInt();
            if(number==1){
                Person person = new Person();
                person.test();
            }
        }
    }
}
