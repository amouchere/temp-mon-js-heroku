package com.amouchere.temperaturemonitoring.repository;


import com.amouchere.temperaturemonitoring.domain.DataByLocation;
import com.amouchere.temperaturemonitoring.domain.Payload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class TempsRepository {

    private static final int MAX = 240;
    private Map<String, LinkedList<Payload>> repo = new HashMap<>();

    public void addTemps(String location, Payload payload) {
        payload.setDateTime(LocalDateTime.now());

        if (repo.containsKey(location)) {
            LinkedList<Payload> temps = repo.get(location);
            temps.add(payload);
            if (temps.size() > MAX) {
                temps.removeFirst();
            }
        } else {
            LinkedList<Payload> list = new LinkedList<>();
            list.add(payload);
            repo.put(location, list);
        }

    }

    public List<DataByLocation> read() {
        return repo.entrySet().stream().map(e -> DataByLocation.builder().location(e.getKey()).data(e.getValue()).build()).collect(Collectors.toList());
    }

    public List<Payload> read(String location) {
        return repo.get(location);
    }

    public List<String> readLocation() {
        return repo.keySet().stream().collect(Collectors.toList());
    }
}

