package com.example.weatherinformationservice.model.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CityWeather {
    
    @Id
    private String name;

    private BigDecimal temp;

    private char unit;

    private String weather;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp dateTime;

    public CityWeather(String name) {
        this.name = name;
    }

    public CityWeather() {
    }
}
