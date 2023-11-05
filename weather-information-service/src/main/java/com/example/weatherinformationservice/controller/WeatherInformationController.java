package com.example.weatherinformationservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.weatherinformationservice.exceptions.CustomException;
import com.example.weatherinformationservice.exceptions.InvalidInputException;
import com.example.weatherinformationservice.model.dao.WeatherInformationDao;
import com.example.weatherinformationservice.model.dto.CityWeatherDto;
import com.example.weatherinformationservice.service.impl.WeatherInformationImpl;
import com.example.weatherinformationservice.utils.SuccessResponseDto;

import jakarta.validation.Valid;


@RestController
@RequestMapping(value = "/weather/v1")
@Validated
public class WeatherInformationController {

    @Autowired
    WeatherInformationImpl weatherInformationImpl;

    @Autowired
    WeatherInformationDao weatherInformationDao;
    
    @PostMapping("/cities")
    public ResponseEntity<SuccessResponseDto> addCityWeatherInformation(@Valid @RequestBody CityWeatherDto cityWeatherDto){
        if(cityWeatherDto.getName() != null && !cityWeatherDto.getName().isEmpty()){
            if(cityWeatherDto.getName().matches("^[0-9]+$")){
                throw new InvalidInputException("BAD REQUEST", 400002, "'name' value should not be numeric string", 400);
            }
            return weatherInformationImpl.saveWeatherInfo(cityWeatherDto);
        } else {
            throw new CustomException("BAD REQUEST", 400001, "Missing required property 'city'", 400);
        }
    }
}
