package main;

import main.presentation.Screen;

public class Main {

    public static void main(String[] args) {
        //Server server = new ServerImpl();
        //server.getWeather("Manchester");

        Screen screen = Inject.screen();
        screen.build();

    }
}
