package com.amouchere.temperaturemonitoring.repository;


import com.amouchere.temperaturemonitoring.domain.Temp;
import com.amouchere.temperaturemonitoring.domain.TempDate;
import com.amouchere.temperaturemonitoring.domain.TempsByLocation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
public class TempsRepository {

    private Map<String, List<TempDate>> repo = new HashMap<>();

    public void addTemps(Temp temp) {
        TempDate newEntry = TempDate.builder().dateTime(LocalDateTime.now()).value(temp.getValue()).build();

        if (repo.containsKey(temp.getLocation())) {
            List<TempDate> temps = repo.get(temp.getLocation());
            temps.add(newEntry);
        } else {
            List<TempDate> list = new ArrayList<>();
            list.add(newEntry);
            repo.put(temp.getLocation(), list);
        }

    }

    public List<TempsByLocation> read() {
        return repo.entrySet().stream().map(e -> TempsByLocation.builder().location(e.getKey()).data(e.getValue()).build()).collect(Collectors.toList());
    }

  //  @PostConstruct
    public void initData(){
        TempDate newEntry1 = TempDate.builder().dateTime(LocalDateTime.now()).value(20).build();
        TempDate newEntry2 = TempDate.builder().dateTime(LocalDateTime.now().minusHours(1)).value(21).build();
        TempDate newEntry3 = TempDate.builder().dateTime(LocalDateTime.now().minusHours(2)).value(16).build();
        TempDate newEntry4 = TempDate.builder().dateTime(LocalDateTime.now().minusHours(3)).value(15).build();
        List<TempDate> list = new ArrayList<>();
        list.add(newEntry1);
        list.add(newEntry2);
        list.add(newEntry3);
        list.add(newEntry4);
        repo.put("ch1", list);

    }
}

