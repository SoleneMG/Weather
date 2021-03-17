package main.data.server;

import com.google.gson.Gson;
import main.data.models.CityWeather;
import main.data.server.models.CityWeatherJson;
import main.data.server.models.NetworkResponse;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServerImpl implements Server {
    private final String emptyUrlPath = "http://api.openweathermap.org/data/2.5/weather?q=cityName&APPID=756d0d2c5b6088e62b63cdd7b1efa538";
    private final Gson gson = new Gson();

    public NetworkResponse<CityWeather> getWeather(String cityName) {
        try {
            String parameterRequest = emptyUrlPath.replace("cityName", cityName);
            URL url = new URL(parameterRequest);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(httpURLConnection.getInputStream());
            String json = readStream(in);
            // System.out.println(json);
            return parseCityWeatherJson(json);
        } catch (IOException e) {
            e.printStackTrace();
            return new NetworkResponse<>(500,null);
        }
    }

    private String readStream(InputStream in) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(in), 1000);
        for (String line = r.readLine(); line != null; line = r.readLine()) {
            sb.append(line);
        }
        in.close();
        return sb.toString();
    }

    private NetworkResponse<CityWeather> parseCityWeatherJson(String json) {
        CityWeatherJson cityWeatherJson = gson.fromJson(json, CityWeatherJson.class);
        int code = cityWeatherJson.cod;
        double tempMin = cityWeatherJson.main.temp_min;
        double tempMax = cityWeatherJson.main.temp_max;
        return new NetworkResponse<>(code, new CityWeather(tempMin, tempMax));
    }

}
