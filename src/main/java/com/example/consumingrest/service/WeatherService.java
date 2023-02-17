package com.example.consumingrest.service;

import com.example.consumingrest.controller.WeatherController;
import com.example.consumingrest.responsemodel.SimpleWeatherResponse;
import com.example.consumingrest.responsemodel.openweathermap.WeatherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    @Value("${apiKey}")
    private String apiKey;
    final RestTemplate restTemplate;
    final String baseUrl = "https://api.openweathermap.org/data/2.5/weather";

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public SimpleWeatherResponse getSimpleWeatherResponse() {
        WeatherResponse weatherResponse = restTemplate.getForObject(
                baseUrl + "?q=Budapest,HU&appid=" + apiKey + "&units=metric&lang=hu", WeatherResponse.class);
        return new SimpleWeatherResponse(weatherResponse);
    }

    public SimpleWeatherResponse getSimpleWeatherResponse(String city) {
        String url = baseUrl + "?q=" + city + "&appid=" + apiKey + "&units=metric&lang=hu";
        WeatherResponse weatherResponse = restTemplate.getForObject(
                url, WeatherResponse.class);
        return new SimpleWeatherResponse(weatherResponse);
    }
}
