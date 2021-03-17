package main.data.server;

import main.data.models.CityWeather;
import main.data.server.models.NetworkResponse;

public interface Server {

    NetworkResponse<CityWeather> getWeather(String cityName);
}
