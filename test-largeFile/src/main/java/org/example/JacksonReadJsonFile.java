package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;
import java.util.Map;

public class JacksonReadJsonFile {

    public static void main(String[] args) {
        try {
            long start = System.currentTimeMillis();
//            File json = new File("C:/Users/zhenghualiang/Desktop/7516_CQRC01_5001010120010000062_1608280869407.txt");
            String filePath = "D:/work/python_src/settle/Output_decrypt.txt";

            File json = new File(filePath);

            ObjectMapper mapper = new ObjectMapper();//此处引入的是jackson中的ObjectMapper类
            Map<String, Object> dataNode = mapper.readValue(json, Map.class);
            //获取json文件中lstOrderBaseInfo这个key对应的value
            //List<Map<String, Object>> data = (List<Map<String, Object>>) dataNode.get("lstOrderBaseInfo");

            System.out.println("耗时：" + (System.currentTimeMillis() - start));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
