package domain;

import main.data.models.CityWeather;
import main.data.server.Server;
import main.data.server.models.NetworkResponse;
import main.domain.ScreenLogic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
public class ScreenLogicTest {
    @Mock
    Server server;
    @InjectMocks
    ScreenLogic screenLogic;

    @ParameterizedTest
    @MethodSource("addGetWeatherParameters")
    public void test_getWeather(String cityName, NetworkResponse<CityWeather> expected) {
        //Given
        Mockito.when(server.getWeather(cityName)).thenReturn(expected);
        //When
        NetworkResponse<CityWeather> result = screenLogic.getWeather(cityName);
        //Then
        Assertions.assertEquals(expected, result);
    }

    private static Stream<Arguments> addGetWeatherParameters() {
        return Stream.of(
                Arguments.of("London", new NetworkResponse<>(200, new CityWeather(7, 10))),
                Arguments.of("Inconnu", new NetworkResponse<>(404, null))
        );
    }
}