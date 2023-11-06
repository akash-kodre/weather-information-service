package com.example.weatherinformationservice.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import com.example.weatherinformationservice.exceptions.CustomException;
import com.example.weatherinformationservice.exceptions.InvalidInputException;
import com.example.weatherinformationservice.exceptions.NoRecordFoundException;
import com.example.weatherinformationservice.model.dao.WeatherInformationDao;
import com.example.weatherinformationservice.model.dto.CityWeatherDto;
import com.example.weatherinformationservice.model.entities.CityWeather;
import com.example.weatherinformationservice.utils.CityListResponseDto;
import com.example.weatherinformationservice.utils.CityResponseDto;
import com.example.weatherinformationservice.utils.ErrorResponseDto;

@SpringBootTest
class WeatherInformationServiceImplTests {

	@Autowired
	WeatherInformationImpl weatherInformationImpl;

	@MockBean
	WeatherInformationDao weatherInformationDao;



	@Test
	public void saveWeatherInfoTest() {
        
        CityWeatherDto cityWeatherDto = new CityWeatherDto();
        cityWeatherDto.setName("Auckland");
        cityWeatherDto.setTemp(new BigDecimal(19.1));
        
        CityWeather cityWeather = new CityWeather();
        cityWeather.setName("Auckland");
        cityWeather.setTemp(new BigDecimal(19.1));
        when(weatherInformationDao.save(any(CityWeather.class))).thenReturn(cityWeather);
        ResponseEntity<CityResponseDto> response = weatherInformationImpl.saveWeatherInfo(cityWeatherDto);
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
	public void testAddCityWeatherInformationMissingCity() {
        
        CityWeatherDto cityWeatherDto = new CityWeatherDto();
        CityWeather cityWeather = new CityWeather();
        when(weatherInformationDao.save(any(CityWeather.class))).thenReturn(cityWeather);
        assertThrows(CustomException.class, () -> {weatherInformationImpl.saveWeatherInfo(cityWeatherDto);});
    }

    @Test
	public void testAddCityWeatherInformationIncorrectCityValue() {
        
        CityWeatherDto cityWeatherDto = new CityWeatherDto();
        cityWeatherDto.setName("123456");
        assertThrows(InvalidInputException.class, () -> {weatherInformationImpl.saveWeatherInfo(cityWeatherDto);});
    }

    @Test
	public void getWeatherRecordsTest() {
        List<CityWeather> cityWeathers = new ArrayList<>();
        CityWeather cityWeather = new CityWeather();
        cityWeather.setName("Auckland");
        cityWeather.setTemp(new BigDecimal(19.1));
        cityWeathers.add(cityWeather);
        when(weatherInformationDao.findAll()).thenReturn(cityWeathers);
        ResponseEntity<CityListResponseDto> response = weatherInformationImpl.getWeatherRecords();
        assertEquals(200, response.getStatusCode().value());
        assertEquals(1, response.getBody().getResult().size());
    }


    @Test
	public void getWeatherRecordsTestNoRecords() {
        List<CityWeather> cityWeathers = new ArrayList<>();
        when(weatherInformationDao.findAll()).thenReturn(cityWeathers);
        assertThrows(NoRecordFoundException.class, () -> {weatherInformationImpl.getWeatherRecords();});
    }

    @Test
	public void getCityWeatherRecordsTest() {
        String cityName = "XYZ";
        CityWeather cityWeather = new CityWeather(cityName);
        when(weatherInformationDao.findById(cityName)).thenReturn(Optional.of(cityWeather));
        ResponseEntity<CityResponseDto> response = weatherInformationImpl.getCityWeatherRecords(cityName);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(cityName, response.getBody().getResult().getName());
    }


    @Test
	public void getCityWeatherRecordsTestNoRecords() {
        String cityName = "XYZ";
        CityWeather cityWeather = new CityWeather();
        when(weatherInformationDao.findById(cityName)).thenReturn(Optional.of(cityWeather));
        assertThrows(NoRecordFoundException.class, () -> {weatherInformationImpl.getCityWeatherRecords("ABC");});
    }
}
