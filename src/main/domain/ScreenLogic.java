package main.domain;

import main.data.models.CityWeather;
import main.data.server.Server;
import main.data.server.models.NetworkResponse;

public class ScreenLogic {
    private final Server server;

    public ScreenLogic(Server server) {
        this.server = server;
    }

    public NetworkResponse<CityWeather> getWeather(String cityName) {
        return server.getWeather(cityName);
    }
}
