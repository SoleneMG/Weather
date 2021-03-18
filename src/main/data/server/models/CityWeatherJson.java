package main.data.server.models;

import java.util.List;

public class CityWeatherJson {
    public MainCityWeatherJson main;
    public int cod;

    public CityWeatherJson(MainCityWeatherJson main, int cod) {
        this.main = main;
        this.cod = cod;
    }
}
