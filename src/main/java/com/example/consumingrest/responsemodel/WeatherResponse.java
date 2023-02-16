package com.example.consumingrest.responsemodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record WeatherResponse(@JsonProperty("weather") List<Weather> weathers, Main main, Wind wind ) {
}
