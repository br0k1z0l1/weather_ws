package com.example.consumingrest.responsemodel;

import com.example.consumingrest.responsemodel.openweathermap.WeatherResponse;
import lombok.Data;

@Data
public class SimpleWeatherResponse {
    private final String city;
    private final long timeStamp;
    private final double feelsLike;
    private final double humidity;
    private final double pressure;
    private final double temp;
    private final double tempMin;
    private final double tempMax;
    private final String description;
    private final String icon;

    public SimpleWeatherResponse(WeatherResponse weatherResponse) {
        this.city = weatherResponse.city();
        this.timeStamp = weatherResponse.timestamp();
        this.feelsLike = weatherResponse.main().feelsLike();
        this.humidity = weatherResponse.main().humidity();
        this.pressure = weatherResponse.main().pressure();
        this.temp = weatherResponse.main().temp();
        this.tempMin = weatherResponse.main().tempMin();
        this.tempMax = weatherResponse.main().tempMax();
        this.description = weatherResponse.weathers().get(0).description();
        this.icon = weatherResponse.weathers().get(0).icon();
    }
}
