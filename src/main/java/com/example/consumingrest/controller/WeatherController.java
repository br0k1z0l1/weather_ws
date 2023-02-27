package com.example.consumingrest.controller;

import com.example.consumingrest.responsemodel.SimpleWeatherResponse;
import com.example.consumingrest.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {
    private final WeatherService weatherService;
    private final Logger logger = LoggerFactory.getLogger(WeatherController.class);

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/api/weather")
    public SimpleWeatherResponse getWeather() {
        logger.info("GET request received for city: Budapest,HU");
        return weatherService.getSimpleWeatherResponse();
    }

    @GetMapping("/api/weather/{city}")
    public SimpleWeatherResponse getLocalWeather(@PathVariable("city") String city){
        logger.info("GET request received for city: " + city);
        return weatherService.getSimpleWeatherResponse(city);
    }

}
