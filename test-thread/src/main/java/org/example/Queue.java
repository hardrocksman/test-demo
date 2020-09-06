package org.example;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class Queue {

    public static void main(String[] args) {
//        ArrayBlockingQueue<Integer> blockingDeque = new ArrayBlockingQueue<Integer>(3);
//
//        for (int i = 0; i < 10; i++) {
//            try {
//                blockingDeque.put(i);
//                System.out.println("add to ArrayBlockingQueue: " + i);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        LinkedBlockingQueue<Integer> blockingDeque = new LinkedBlockingQueue<Integer>(3);

        for (int i = 0; i < 10; i++) {
            try {
                blockingDeque.put(i);
                System.out.println("add to LinkedBlockingQueue: " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
