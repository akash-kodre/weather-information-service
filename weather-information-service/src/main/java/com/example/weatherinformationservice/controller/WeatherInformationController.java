package com.example.weatherinformationservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.weatherinformationservice.exceptions.CustomException;
import com.example.weatherinformationservice.exceptions.InvalidInputException;
import com.example.weatherinformationservice.model.dao.WeatherInformationDao;
import com.example.weatherinformationservice.model.dto.CityWeatherDto;
import com.example.weatherinformationservice.service.impl.WeatherInformationImpl;
import com.example.weatherinformationservice.utils.CityListResponseDto;
import com.example.weatherinformationservice.utils.CityResponseDto;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;


@RestController
@RequestMapping(value = "/weather/v1")
@Validated
public class WeatherInformationController {

    @Autowired
    WeatherInformationImpl weatherInformationImpl;

    @Autowired
    WeatherInformationDao weatherInformationDao;
    
    @PostMapping("/cities")
    public ResponseEntity<CityResponseDto> addCityWeatherInformation(@Valid @RequestBody CityWeatherDto cityWeatherDto){
        return weatherInformationImpl.saveWeatherInfo(cityWeatherDto);
    }

    @GetMapping("/cities")
    public ResponseEntity<CityListResponseDto> fetchAllCityWeatherInformation(){
        return weatherInformationImpl.getWeatherRecords();
    }

    @GetMapping("/cities/{city}")
    public ResponseEntity<CityResponseDto> fetchCityWeatherInformation(@NotBlank @PathVariable ("city") String city){
        return weatherInformationImpl.getCityWeatherRecords(city);
    }
}
