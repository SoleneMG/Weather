package main.data.server;

import java.net.MalformedURLException;
import java.net.URL;

public class ServerImpl implements Server{

    private void callServer() {
        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=756d0d2c5b6088e62b63cdd7b1efa538");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
