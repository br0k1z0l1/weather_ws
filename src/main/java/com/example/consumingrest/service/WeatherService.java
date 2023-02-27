package com.example.consumingrest.service;

import com.example.consumingrest.responsemodel.SimpleWeatherResponse;
import com.example.consumingrest.responsemodel.openweathermap.WeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Service
public class WeatherService {
    @Value("${apiKey}")
    private String apiKey;
    private final RestTemplate restTemplate;
    private final String baseUrl = "https://api.openweathermap.org/data/2.5/weather";
    private final Logger logger = LoggerFactory.getLogger(WeatherService.class);
    private final CacheManager cacheManager;

    @Autowired
    public WeatherService(RestTemplate restTemplate, CacheManager cacheManager) {
        this.restTemplate = restTemplate;
        this.cacheManager = cacheManager;
    }

    @Cacheable(value = "cities", key = "'Budapest,HU'")
    public SimpleWeatherResponse getSimpleWeatherResponse() {
        return getSimpleWeatherResponse("Budapest,HU");
    }

    @Cacheable(value = "cities", key = "#city")
    public SimpleWeatherResponse getSimpleWeatherResponse(String city) {
        String url = baseUrl + "?q=" + city + "&appid=" + apiKey + "&units=metric&lang=hu";
        logger.info("Fetching weather Data from API for '{}'...", city);
        WeatherResponse weatherResponse = restTemplate.getForObject(
                url, WeatherResponse.class);
        return new SimpleWeatherResponse(weatherResponse);
    }

    @CacheEvict(value = "cities", allEntries = true)
    @Scheduled(timeUnit = TimeUnit.MINUTES, fixedRateString = "${caching.spring.citiesTTL}")
    public void emptyCitiesCache() {
        logger.info("emptying cities cache");
    }

}
