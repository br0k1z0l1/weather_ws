package com.example.consumingrest.controller;

import com.example.consumingrest.responsemodel.SimpleWeatherResponse;
import com.example.consumingrest.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {
    final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/api/weather")
    public SimpleWeatherResponse getWeather() {
        return weatherService.getSimpleWeatherResponse();
    }

}
