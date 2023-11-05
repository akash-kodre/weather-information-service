package com.example.weatherinformationservice.service;

import org.springframework.http.ResponseEntity;

import com.example.weatherinformationservice.model.dto.CityWeatherDto;
import com.example.weatherinformationservice.utils.SuccessResponseDto;

public interface WeatherInformationService {

    ResponseEntity<SuccessResponseDto> saveWeatherInfo(CityWeatherDto cityDto);
}
