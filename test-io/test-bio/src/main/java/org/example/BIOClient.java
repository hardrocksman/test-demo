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


            //reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("clientSocket started: " + stringNowTime());
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            inputContent = "qqqqqqq";
            for (int i = 0; i < 10000 ; i++) {
                System.out.println("发送数据");
                inputContent = stringNowTime() + ":index" + count + "message:" + inputContent + "\n";
                writer.write(inputContent);
                writer.flush();

                Thread.sleep(1000);
            }


//            while (((inputContent = reader.readLine()) != null) && count < 2) {
//                count++;
//            }
        } catch (Exception e) {
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
        client.initBIOClient("10.60.46.174", 8888);
    }
}
