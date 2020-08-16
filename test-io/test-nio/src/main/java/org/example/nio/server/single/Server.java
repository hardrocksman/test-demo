package org.example.nio.server.single;

import java.io.IOException;

public class Server {

    public static void main(String[] args) {
        try {
            new Thread(new Reactor(9999)).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
