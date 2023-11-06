package com.example.weatherinformationservice.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.weatherinformationservice.exceptions.CustomException;
import com.example.weatherinformationservice.exceptions.InvalidInputException;
import com.example.weatherinformationservice.exceptions.NoRecordFoundException;
import com.example.weatherinformationservice.model.dao.WeatherInformationDao;
import com.example.weatherinformationservice.model.dto.CityWeatherDto;
import com.example.weatherinformationservice.model.entities.CityWeather;
import com.example.weatherinformationservice.model.mapper.WeatherInformationMapper;
import com.example.weatherinformationservice.service.WeatherInformationService;
import com.example.weatherinformationservice.utils.CityListResponseDto;
import com.example.weatherinformationservice.utils.CityResponseDto;

@Service
public class WeatherInformationImpl implements WeatherInformationService{

    @Autowired
    WeatherInformationDao weatherInformationDao;

    @Override
    public ResponseEntity<CityResponseDto> saveWeatherInfo(CityWeatherDto cityWeatherDto) {
        if(cityWeatherDto.getName() != null && !cityWeatherDto.getName().isEmpty()){
            if(cityWeatherDto.getName().matches("^[0-9]+$")){
                throw new InvalidInputException("BAD REQUEST", 400002, "'name' value should not be numeric string", 400);
            }
            CityWeather cityWeather = WeatherInformationMapper.convertCityWeatherDtoToDao(cityWeatherDto);
            cityWeatherDto.setDateTime(cityWeather.getDateTime());
            cityWeather = weatherInformationDao.save(cityWeather);
            CityResponseDto cityResponseDto = new CityResponseDto();
            cityResponseDto.setSuccess(true);
            cityResponseDto.setResult(cityWeatherDto);
            return new ResponseEntity<CityResponseDto>(cityResponseDto,HttpStatusCode.valueOf(200));
        } else {
            throw new CustomException("BAD REQUEST", 400001, "Missing required property 'name'", 400);
        }        
    }

    @Override
    public ResponseEntity<CityListResponseDto>  getWeatherRecords() {
        List<CityWeather> cityWeathers = weatherInformationDao.findAll();
        if(cityWeathers.isEmpty()){
            throw new NoRecordFoundException("NOT FOUND", 404001, "No records found, first create new weather record.", 404);
        }
            List<CityWeatherDto> cityWeatherDtos = WeatherInformationMapper.convertCityWeatherDaosToDtoList(cityWeathers);
            CityListResponseDto cityListResponseDto = new CityListResponseDto();
            cityListResponseDto.setSuccess(true);
            cityListResponseDto.setResult(cityWeatherDtos);
            return new ResponseEntity<CityListResponseDto>(cityListResponseDto,HttpStatusCode.valueOf(200));
    }


    @Override
    public ResponseEntity<CityResponseDto> getCityWeatherRecords(String city) {
        if(city.matches("^[0-9]+$")){
            throw new InvalidInputException("BAD REQUEST", 400004, "Parameter 'city' value must not be numeric string", 400);
        }
        Optional<CityWeather> cityWeather = weatherInformationDao.findById(city);
        if(cityWeather.isPresent()){
            CityWeatherDto cityWeatherDto = WeatherInformationMapper.convertCityWeatherDaoToDto(cityWeather);
            CityResponseDto cityResponseDto = new CityResponseDto();
            cityResponseDto.setSuccess(true);
            cityResponseDto.setResult(cityWeatherDto);
            return new ResponseEntity<CityResponseDto>(cityResponseDto,HttpStatusCode.valueOf(200));
        } else{
            throw new NoRecordFoundException("NOT FOUND", 404002, "No weather record present for "+city, 404);
        }    
    }
}
