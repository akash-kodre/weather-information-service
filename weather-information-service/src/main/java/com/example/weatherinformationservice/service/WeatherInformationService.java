package com.example.weatherinformationservice.service;

import org.springframework.http.ResponseEntity;

import com.example.weatherinformationservice.model.dto.CityWeatherDto;
import com.example.weatherinformationservice.utils.CityListResponseDto;
import com.example.weatherinformationservice.utils.CityResponseDto;

public interface WeatherInformationService {

    ResponseEntity<CityResponseDto> saveWeatherInfo(CityWeatherDto cityDto);
    ResponseEntity<CityListResponseDto> getWeatherRecords();
    ResponseEntity<CityResponseDto> getCityWeatherRecords(String city);
}
