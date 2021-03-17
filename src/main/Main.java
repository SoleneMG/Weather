package main;

import main.data.server.Server;
import main.data.server.ServerImpl;

public class Main {

    public static void main(String[] args) {
        Server server = new ServerImpl();
        server.getWeather("Manchester");

    }
}
