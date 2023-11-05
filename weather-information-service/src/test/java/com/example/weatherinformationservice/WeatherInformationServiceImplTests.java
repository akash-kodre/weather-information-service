package com.example.weatherinformationservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import com.example.weatherinformationservice.model.dao.WeatherInformationDao;
import com.example.weatherinformationservice.model.dto.CityWeatherDto;
import com.example.weatherinformationservice.model.entities.CityWeather;
import com.example.weatherinformationservice.service.impl.WeatherInformationImpl;
import com.example.weatherinformationservice.utils.SuccessResponseDto;

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
        ResponseEntity<SuccessResponseDto> response = weatherInformationImpl.saveWeatherInfo(cityWeatherDto);
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
	public void testAddCityWeatherInformationMissingCity() {
        
        CityWeatherDto cityWeatherDto = new CityWeatherDto();
        CityWeather cityWeather = new CityWeather();
        when(weatherInformationDao.save(any(CityWeather.class))).thenReturn(cityWeather);
        ResponseEntity<SuccessResponseDto> response = weatherInformationImpl.saveWeatherInfo(cityWeatherDto);
        assertEquals(200, response.getStatusCode().value());
    }
}
