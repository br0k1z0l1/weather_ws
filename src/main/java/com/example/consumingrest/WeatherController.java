package com.example.consumingrest;

import com.example.consumingrest.responsemodel.SimpleWeatherResponse;
import com.example.consumingrest.responsemodel.openweathermap.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class WeatherController {
    final RestTemplate restTemplate;
    @Value("${apiKey}")
    private String apiKey;

    @Autowired
    public WeatherController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/api/weather")
    public SimpleWeatherResponse getWeather() {
        WeatherResponse weatherResponse = restTemplate.getForObject(
                "https://api.openweathermap.org/data/2.5/weather?q=Budapest,HU&appid=" + apiKey + "&units=metric&lang=hu", WeatherResponse.class);
    return new SimpleWeatherResponse(weatherResponse);
    }
}
