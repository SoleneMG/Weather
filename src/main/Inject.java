package main;

import main.data.server.Server;
import main.data.server.ServerImpl;

public class Inject {
    private static final Server server = new ServerImpl();

    public static Server server(){
        return server;
    }
}
