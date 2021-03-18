package main.data.server;

import com.google.gson.Gson;
import main.data.models.CityWeather;
import main.data.server.models.CityWeatherJson;
import main.data.server.models.NetworkResponse;
import main.data.server.utils.ConvertTemperature;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServerImpl implements Server {
    private final String emptyUrlPath = "http://api.openweathermap.org/data/2.5/weather?q=cityName&APPID=756d0d2c5b6088e62b63cdd7b1efa538";
    private final Gson gson = new Gson();

    public NetworkResponse<CityWeather> getWeather(String cityName) {
        InputStream in = null;
        try {
            String parameterRequest = emptyUrlPath.replace("cityName", cityName);
            URL url = new URL(parameterRequest);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(httpURLConnection.getInputStream());
            String json = readStream(in);
           System.out.println( parseCityWeatherJson(json));
            return parseCityWeatherJson(json);
        } catch (Exception e) {
            System.out.println("Ville inconnue");
            return new NetworkResponse<>(500,null);
        } finally {
            try {
                if(in != null){
                    in.close();
                }
            } catch (IOException ioException) {
                System.out.println("Echec fermeture");
                ioException.printStackTrace();
            }
        }
    }

    private String readStream(InputStream in) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(in), 1000);
        for (String line = r.readLine(); line != null; line = r.readLine()) {
            sb.append(line);
        }
        return sb.toString();
    }

    private NetworkResponse<CityWeather> parseCityWeatherJson(String json) {
        ConvertTemperature convertTemperature = new ConvertTemperature();
        CityWeatherJson cityWeatherJson = gson.fromJson(json, CityWeatherJson.class);

        int code = cityWeatherJson.cod;
        double tempMin = convertTemperature.kelvinToDegree(cityWeatherJson.main.temp_min);
        double tempMax = convertTemperature.kelvinToDegree(cityWeatherJson.main.temp_max);
        return new NetworkResponse<>(code, new CityWeather(tempMin, tempMax));
    }

}
