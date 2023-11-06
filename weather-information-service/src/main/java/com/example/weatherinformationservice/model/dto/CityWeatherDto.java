package com.example.weatherinformationservice.model.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.springframework.lang.NonNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CityWeatherDto {

    @NonNull
    private String name;

    private BigDecimal temp;

    private char unit;

    private String weather;

    private Timestamp dateTime;
}
