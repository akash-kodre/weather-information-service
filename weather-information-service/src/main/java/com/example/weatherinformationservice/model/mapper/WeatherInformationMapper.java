package com.example.weatherinformationservice.model.mapper;

import java.sql.Timestamp;

import com.example.weatherinformationservice.model.dto.CityWeatherDto;
import com.example.weatherinformationservice.model.entities.CityWeather;

public abstract class WeatherInformationMapper {
    
    private WeatherInformationMapper(){
    }

    public static CityWeather convertCityWeatherDtoToDao(CityWeatherDto cityWeatherDto){

        CityWeather cityWeather = new CityWeather();
        cityWeather.setName(cityWeatherDto.getName());
        cityWeather.setTemp(cityWeatherDto.getTemp());
        cityWeather.setUnit(cityWeatherDto.getUnit());
        cityWeather.setWeather(cityWeatherDto.getWeather());
        cityWeather.setDateTime(new Timestamp(System.currentTimeMillis()));
        return cityWeather;
    }
}