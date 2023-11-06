package com.example.weatherinformationservice.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.weatherinformationservice.model.entities.CityWeather;


public interface WeatherInformationDao extends JpaRepository<CityWeather, String> {
}
