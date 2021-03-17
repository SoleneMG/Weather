package main.data.server.models;

import java.util.List;

public class CityWeatherJson {
    public MainCityWeatherJson main;
    public int cod;

    public CityWeatherJson(MainCityWeatherJson main, int cod) {
        this.main = main;
        this.cod = cod;
    }

    @Override
    public String toString() {
        return "CityWeatherJson{" +
                "main=" + main +
                ", code=" + cod +
                '}';
    }
}
