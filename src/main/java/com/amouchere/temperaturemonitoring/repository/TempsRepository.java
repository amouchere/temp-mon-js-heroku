package com.amouchere.temperaturemonitoring.repository;


import com.amouchere.temperaturemonitoring.domain.Temp;
import com.amouchere.temperaturemonitoring.domain.TempDate;
import com.amouchere.temperaturemonitoring.domain.TempsByLocation;
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
    private Map<String, LinkedList<TempDate>> repo = new HashMap<>();

    public void addTemps(Temp temp) {
        TempDate newEntry = TempDate.builder().dateTime(LocalDateTime.now()).value(temp.getValue()).build();

        if (repo.containsKey(temp.getLocation())) {
            LinkedList<TempDate> temps = repo.get(temp.getLocation());
            temps.add(newEntry);
            if (temps.size() > MAX ) {
                temps.removeFirst();
            }
        } else {
            LinkedList<TempDate> list = new LinkedList<>();
            list.add(newEntry);
            repo.put(temp.getLocation(), list);
        }

    }

    public List<TempsByLocation> read() {
        return repo.entrySet().stream().map(e -> TempsByLocation.builder().location(e.getKey()).data(e.getValue()).build()).collect(Collectors.toList());
    }
}

