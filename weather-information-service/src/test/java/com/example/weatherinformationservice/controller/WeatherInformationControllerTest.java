package com.example.weatherinformationservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.weatherinformationservice.model.dto.CityWeatherDto;
import com.example.weatherinformationservice.service.impl.WeatherInformationImpl;
import com.example.weatherinformationservice.utils.CityListResponseDto;
import com.example.weatherinformationservice.utils.CityResponseDto;

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
            .thenReturn(new ResponseEntity<>(new CityResponseDto(), HttpStatus.OK));
        ResponseEntity<CityResponseDto> response = weatherInformationController.addCityWeatherInformation(cityWeatherDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    
    @Test
    public void testAddCityWeatherInformationMissingCity() {
       
        CityWeatherDto cityWeatherDto = new CityWeatherDto();
        
        ResponseEntity<CityResponseDto> response = weatherInformationController.addCityWeatherInformation(cityWeatherDto);
        assertNull(response);
    }

    @Test
    public void testfetchAllCityWeatherInformationHappyCase() {
        CityListResponseDto cityListResponseDto =  new CityListResponseDto();
        cityListResponseDto.setSuccess(true);
        ResponseEntity<CityListResponseDto> resp = new ResponseEntity<CityListResponseDto>(cityListResponseDto,HttpStatusCode.valueOf(200));
        when(weatherInformationImpl.getWeatherRecords())
            .thenReturn(resp);
        ResponseEntity<CityListResponseDto> response = weatherInformationController.fetchAllCityWeatherInformation();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


    @Test
    public void testfetchCityWeatherInformationHappyCase() {
        CityResponseDto cityResponseDto =  new CityResponseDto();
        cityResponseDto.setSuccess(true);
        ResponseEntity<CityResponseDto> resp = new ResponseEntity<CityResponseDto>(cityResponseDto,HttpStatusCode.valueOf(200));
        when(weatherInformationImpl.getCityWeatherRecords("XYZ"))
            .thenReturn(resp);
        ResponseEntity<CityResponseDto> response = weatherInformationController.fetchCityWeatherInformation("XYZ");
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


    @Test
    public void testfetchCityWeatherInformationNoRecord() {
        ResponseEntity<CityResponseDto> response = weatherInformationController.fetchCityWeatherInformation("NewCity");
        assertNull(response);
    }
}
