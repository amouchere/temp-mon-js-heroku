package com.amouchere.temperaturemonitoring.domain;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class TempsByLocation {

    private String location;
    private List<TempDate> data;
}
