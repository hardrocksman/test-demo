package org.example;

import com.google.gson.Gson;
import com.zhl.test.largeFile.model.DetailRecord;

import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        System.out.println( "Hello World!" );
        try {
            //将写入转化为流的形式
            BufferedWriter bw = new BufferedWriter(new FileWriter("E:/LARGE.txt"));
            //一次写一行
            Gson gson = new Gson();
            for (int i = 0; i < 1000000; i++) {
                DetailRecord record = DetailRecord.buildData();
                String ss = gson.toJson(record);
                bw.write(ss);
                bw.newLine();  //换行用
            }
            //关闭流
            bw.close();
            System.out.println("写入成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
