package org.example;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import com.sun.org.apache.bcel.internal.generic.BREAKPOINT;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class JacksonReadLargeJson {

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();

        //String filePath = "C:/Users/zhenghualiang/Desktop/7516_CQRC01_5001010120010000062_1608280869407.txt";

        String filePath = "D:/work/python_src/settle/Output.txt";

        JsonFactory f = new JsonFactory();
        JsonParser parser = f.createParser(new FileInputStream(new File(filePath)));// .createJsonParser(new File(filePath));

        JsonToken jsonToken = null;
        while (!parser.isClosed()) {
            jsonToken = parser.nextToken();


            String fieldName = parser.getCurrentName();
            String value = parser.getValueAsString();

            System.out.println("jsonToken:" + jsonToken + ",field:" + fieldName + ",value:" + value);

            if ("DetailRecord".equals(fieldName)) {
                // parser.nextToken();
                // messages is array, loop until equals "]"

                jsonToken = parser.nextToken();
                if (jsonToken == JsonToken.START_ARRAY) {
                    while (true) {
                        jsonToken = parser.nextToken();

                        if (jsonToken == JsonToken.END_ARRAY) {
                            break;
                        }

                        if (jsonToken == JsonToken.START_OBJECT) {
                            System.out.println("=========================================开始一次新的Object解析===========================");
                            while (true) {
                                jsonToken = parser.nextToken();

                                if (jsonToken == JsonToken.END_OBJECT) {
                                    System.out.println("======================================一次解析Object结束===================================");
                                    break;
                                }

                                String k = "";
                                String v = "";

                                k = parser.getCurrentName();

                                jsonToken = parser.nextToken();
                                Object o = parser.getText();

                                if (o != null) {
                                    v = o.toString();
                                }

                                System.out.println("--------------------key:" + k + ",value:" + v);
                            }
                        }
                    }
                }
            }
        }

        parser.close();
        System.out.println("解析完成耗时：" + (System.currentTimeMillis() - start));


//        JsonToken current;
//        current = jp.nextToken();
//        if (current != JsonToken.START_OBJECT) {
//            System.out.println("Error: root should be object: quiting.");
//            return;
//        }
//        while (jp.nextToken() != JsonToken.END_OBJECT) {
//            String fieldName = jp.getCurrentName();
//            // move from field name to field value
//            current = jp.nextToken();
//            if (fieldName.equals("records")) {
//                if (current == JsonToken.START_ARRAY) {
//                    // For each of the records in the array
//                    while (jp.nextToken() != JsonToken.END_ARRAY) {
//                        // read the record into a tree model,
//                        // this moves the parsing position to the end of it
//                        JsonNode node = jp.readValueAsTree();
//                        // And now we have random access to everything in the object
////                        System.out.println("field1: " + node.get("field1").get.getValueAsText());
////                        System.out.println("field2: " + node.get("field2").getValueAsText());
//                    }
//                } else {
//                    System.out.println("Error: records should be an array: skipping.");
//                    jp.skipChildren();
//                }
//            } else {
//                System.out.println("Unprocessed property: " + fieldName);
//                jp.skipChildren();
//            }
//        }
    }
}
