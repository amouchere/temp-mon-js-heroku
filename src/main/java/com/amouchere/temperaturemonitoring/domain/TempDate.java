package com.amouchere.temperaturemonitoring.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TempDate {

    private LocalDateTime dateTime;
    private int value;
}
