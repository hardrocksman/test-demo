package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BIOServer {

    public static void main(String[] args) {
        BIOServer server = new BIOServer();
        server.initBIOServer(8888);
    }

    public void initBIOServer(int port) {
        //服务端Socket
        ServerSocket serverSocket = null;
        //客户端socket
        Socket socket = null;
        BufferedReader reader = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println(stringNowTime() + ":serverSocket started");
            while (true) {
                socket = serverSocket.accept();
                System.out.println(stringNowTime() + ":id:" + socket.hashCode() + " Clientsocket connected");
//                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                while ((inputContent = reader.readLine()) != null) {
//                    System.out.println("收到id为" + socket.hashCode() + "  "+inputContent);
//                    count++;
//                }
//                System.out.println("id为" + socket.hashCode()+ "的Clientsocket "+stringNowTime()+"读取结束");

                new Thread(new ReadThread(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String stringNowTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }

    class ReadThread implements Runnable {

        private Socket socket;

        public ReadThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                String inputContent = null;
//                while ((inputContent = reader.readLine()) != null) {
//                    System.out.println("get id:" + socket.hashCode() + ":content:" + inputContent);
//
//                    //writer.write(inputContent);
//                }
                int count = 0;
                while(true) {
                    Thread.sleep(5000);
                    inputContent = stringNowTime() + ":index" + count + "message:" + count + "\n";
                    count++;
                    writer.write(inputContent);
                    writer.flush();
                }

                // System.out.println("id:" + socket.hashCode() + ":Clientsocket " + stringNowTime() + "read eof");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public String stringNowTime() {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return format.format(new Date());
        }
    }
}
