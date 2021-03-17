package main.data.server.utils;

public class ConvertTemperature {

    public double kelvinToDegree(double tempKelvin){
        return tempKelvin-273.15;
    }
    public double degreeToKelvin(double tempInDegree){
        return tempInDegree+273.15;
    }
}
