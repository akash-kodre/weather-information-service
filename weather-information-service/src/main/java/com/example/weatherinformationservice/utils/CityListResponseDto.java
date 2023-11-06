package com.example.weatherinformationservice.utils;

import java.util.List;

import com.example.weatherinformationservice.model.dto.CityWeatherDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityListResponseDto {
    
    private boolean success;
    private List<CityWeatherDto> result;
}
