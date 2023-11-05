package com.example.weatherinformationservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.weatherinformationservice.exceptions.CustomException;
import com.example.weatherinformationservice.model.dto.CityWeatherDto;
import com.example.weatherinformationservice.service.impl.WeatherInformationImpl;
import com.example.weatherinformationservice.utils.SuccessResponseDto;

@ExtendWith(MockitoExtension.class)
public class WeatherInformationControllerTest {
    
    @InjectMocks
    private WeatherInformationController weatherInformationController;

    @Mock
    private WeatherInformationImpl weatherInformationImpl;


    @Test
    public void testAddCityWeatherInformationValid() {
        
        
        CityWeatherDto cityWeatherDto = new CityWeatherDto();
        cityWeatherDto.setName("Auckland");
        when(weatherInformationImpl.saveWeatherInfo(any(CityWeatherDto.class)))
            .thenReturn(new ResponseEntity<>(new SuccessResponseDto(), HttpStatus.OK));
        ResponseEntity<SuccessResponseDto> response = weatherInformationController.addCityWeatherInformation(cityWeatherDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    
    @Test
    public void testAddCityWeatherInformationMissingCity() {
       
        CityWeatherDto cityWeatherDto = new CityWeatherDto();
        CustomException exception = assertThrows(CustomException.class, () -> {
            weatherInformationController.addCityWeatherInformation(cityWeatherDto);
        });
        assertEquals("Missing required property 'city'", exception.getMessage());
    }
}
