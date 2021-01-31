package org.example;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BIOClient {

    public void initBIOClient(String host, int port) {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        Socket socket = null;
        String inputContent;
        int count = 0;
        try {
//            reader = new BufferedReader(new InputStreamReader(System.in));
            socket = new Socket(host, port);
            //socket.setSoTimeout(300000);
            System.out.println("clientSocket started: " + stringNowTime());
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            inputContent = "hello";

//            while(true) {
//                Thread.sleep(1000);
//                inputContent = stringNowTime() + ":index" + count + "message:" + inputContent + "\n";
//                writer.write(inputContent);
//                writer.flush();
//            }

//            for (int i = 0; i < 5; i++) {
//                Thread.sleep(1000);

            String content = null;
            while ((content = reader.readLine()) != null) {
                System.out.println("get id:" + socket.hashCode() + ":content:" + content);
//                Thread.sleep(1000);
//                writer.write(inputContent);
            }
//            }


//            while (((inputContent = reader.readLine()) != null) && count < 2) {
//                count++;
//            }
        } catch (Exception e) {
            System.out.println(new Date());
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                //reader.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String stringNowTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }

    public static void main(String[] args) {
        BIOClient client = new BIOClient();
        client.initBIOClient("localhost", 8888);
//        client.initBIOClient("localhost", 8888);
    }
}
