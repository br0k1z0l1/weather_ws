package com.example.consumingrest;

import com.example.consumingrest.responsemodel.openweathermap.WeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableCaching
@EnableScheduling
public class ConsumingRestApplication {

    @Value("${apiKey}")
    private String apiKey;

    static final Logger log = LoggerFactory.getLogger(ConsumingRestApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ConsumingRestApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
            WeatherResponse weatherResponse = restTemplate.getForObject(
                    "https://api.openweathermap.org/data/2.5/weather?q=Budapest,HU&appid=" + apiKey + "&units=metric&lang=hu", WeatherResponse.class);
            log.info(weatherResponse.toString());
        };
    }

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("cities");
    }

}