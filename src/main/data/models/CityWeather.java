package main.data.models;

public class CityWeather {
    public final double tempMin;
    public final double tempMax;

    public CityWeather(double tempMin, double tempMax) {
        this.tempMin = tempMin;
        this.tempMax = tempMax;
    }

    @Override
    public String toString() {
        return "CityWeather{" +
                ", temp_min=" + tempMin +
                ", temp_max=" + tempMax +
                '}';
    }
}
