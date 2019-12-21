package com.amouchere.temperaturemonitoring.controller;

import com.amouchere.temperaturemonitoring.domain.Payload;
import com.amouchere.temperaturemonitoring.domain.DataByLocation;
import com.amouchere.temperaturemonitoring.repository.TempsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class TempController {

    private TempsRepository repo;

    @Autowired
    public TempController(TempsRepository repo) {
        this.repo = repo;
    }

    @PostMapping("/data")
    public void temp(@RequestBody Payload temp, @RequestParam("location") String location) {
        log.info(" ! new data for location {} -> {}", location, temp.toString());
        repo.addTemps(location, temp);
        repo.read();
    }

    @GetMapping("/data")
    public List<DataByLocation> temp() {
        log.info("Get data !");
        return repo.read();

    }

}
