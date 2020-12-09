package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReadThread implements Runnable{

    private Socket socket;

    public ReadThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String inputContent = null;
            int count = 0;
            while ((inputContent = reader.readLine()) != null) {
                System.out.println("收到id为" + socket.hashCode() + "  " + inputContent);
                count++;
            }
            System.out.println("id为" + socket.hashCode() + "的Clientsocket " + stringNowTime() + "读取结束");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String stringNowTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }
}
