package com.example.weatherinformationservice.model.mapper;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

   
    public static List<CityWeatherDto> convertCityWeatherDaosToDtoList(List<CityWeather> cityWeathers){
        List<CityWeatherDto> cityWeatherDtos = new ArrayList<CityWeatherDto>();
        for(CityWeather cityWeather : cityWeathers){
            CityWeatherDto cityWeatherDto = new CityWeatherDto();
            cityWeatherDto.setName(cityWeather.getName());
            cityWeatherDto.setTemp(cityWeather.getTemp());
            cityWeatherDto.setUnit(cityWeather.getUnit());
            cityWeatherDto.setWeather(cityWeather.getWeather());
            cityWeatherDto.setDateTime(cityWeather.getDateTime());
            cityWeatherDtos.add(cityWeatherDto);
        }        
        return cityWeatherDtos;
    }


    public static CityWeatherDto convertCityWeatherDaoToDto(Optional<CityWeather> cityWeather){
        CityWeatherDto cityWeatherDto = new CityWeatherDto();
        cityWeatherDto.setName(cityWeather.get().getName());
        cityWeatherDto.setTemp(cityWeather.get().getTemp());
        cityWeatherDto.setUnit(cityWeather.get().getUnit());
        cityWeatherDto.setWeather(cityWeather.get().getWeather());
        cityWeatherDto.setDateTime(cityWeather.get().getDateTime());        
        return cityWeatherDto;
    }
}