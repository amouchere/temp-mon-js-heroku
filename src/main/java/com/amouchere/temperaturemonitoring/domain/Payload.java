package com.amouchere.temperaturemonitoring.domain;

import lombok.Builder;

import java.time.LocalDateTime;

@lombok.Data
@Builder
public class Payload {

    private LocalDateTime dateTime;
    private float temperature;
    private float humidity;
}
