package com.example.weatherinformationservice.utils;

import com.example.weatherinformationservice.model.dto.CityWeatherDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityResponseDto {
    
    private boolean success;
    private CityWeatherDto result;
}
