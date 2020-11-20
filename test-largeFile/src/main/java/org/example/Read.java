package org.example;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;

public class Read {

    public static void main(String[] args) {
        System.out.println("start");
        try {
            long start = System.currentTimeMillis();
            LineIterator lineIterator = FileUtils.lineIterator(new File("E:\\LARGE.txt"), "utf-8");
            while (lineIterator.hasNext()) {
                String result = lineIterator.next();
                System.out.println(result);
            }
            System.out.println("总耗时:" + (System.currentTimeMillis() - start));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
