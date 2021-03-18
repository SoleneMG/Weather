package main;

import main.data.server.Server;
import main.data.server.ServerImpl;
import main.domain.ScreenLogic;
import main.presentation.Screen;

public class Inject {
    private static final Server SERVER = new ServerImpl();
    private static final Screen SCREEN = new Screen();

    public static Server server(){
        return SERVER;
    }

    public static ScreenLogic screenLogic(){
        return new ScreenLogic(SERVER);
    }

    public static Screen screen(){
        return SCREEN;
    }
}
