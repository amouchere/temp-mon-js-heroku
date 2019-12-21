package com.amouchere.temperaturemonitoring.domain;

import lombok.Builder;

import java.util.List;

@lombok.Data
@Builder
public class DataByLocation {

    private String location;
    private List<Payload> data;
}
