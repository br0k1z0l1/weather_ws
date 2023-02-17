package com.example.consumingrest.responsemodel;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Main(
        double temp,
        @JsonProperty("temp_min") double tempMin,
        @JsonProperty("temp_max") double tempMax,
        @JsonProperty("feels_like") double feelsLike,
        double humidity,
        double pressure) {
}
