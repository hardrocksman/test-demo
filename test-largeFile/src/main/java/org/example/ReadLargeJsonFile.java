package org.example;

import com.google.gson.stream.JsonReader;

import java.io.*;

public class ReadLargeJsonFile {


    public static void main(String[] args) throws FileNotFoundException {
        String filePath = "C:/Users/zhenghualiang/Desktop/7516_CQRC01_5001010120010000062_1608280869407.txt";

        InputStreamReader in = new InputStreamReader(new FileInputStream(filePath));
        if (in != null) {
            JsonReader reader = new JsonReader(in);
            try {
                reader.beginObject();
                while (reader.hasNext()) {
                    final String value = reader.nextName();
                    System.out.println(value);
                }

                reader.endObject();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("文件流传入为null。。。。。");
        }

    }
}
