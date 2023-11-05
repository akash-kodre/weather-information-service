package com.example.weatherinformationservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.weatherinformationservice.model.dao.WeatherInformationDao;
import com.example.weatherinformationservice.model.dto.CityWeatherDto;
import com.example.weatherinformationservice.model.entities.CityWeather;
import com.example.weatherinformationservice.model.mapper.WeatherInformationMapper;
import com.example.weatherinformationservice.service.WeatherInformationService;
import com.example.weatherinformationservice.utils.SuccessResponseDto;

@Service
public class WeatherInformationImpl implements WeatherInformationService{

    @Autowired
    WeatherInformationDao weatherInformationDao;

    @Override
    public ResponseEntity<SuccessResponseDto> saveWeatherInfo(CityWeatherDto cityWeatherDto) {
        CityWeather cityWeather = WeatherInformationMapper.convertCityWeatherDtoToDao(cityWeatherDto);
        cityWeatherDto.setDateTime(cityWeather.getDateTime());
        cityWeather = weatherInformationDao.save(cityWeather);
        SuccessResponseDto successResponseDto = new SuccessResponseDto();
        successResponseDto.setSuccess(true);
        successResponseDto.setResult(cityWeatherDto);
        return new ResponseEntity<SuccessResponseDto>(successResponseDto,HttpStatusCode.valueOf(200));
    }
}
