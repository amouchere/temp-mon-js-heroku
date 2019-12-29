package com.amouchere.temperaturemonitoring.controller;

import com.amouchere.temperaturemonitoring.domain.DataByLocation;
import com.amouchere.temperaturemonitoring.domain.Payload;
import com.amouchere.temperaturemonitoring.repository.TempsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("api")
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class TempController {

    @Autowired
    private BuildProperties buildProperties;

    private TempsRepository repo;

    @Autowired
    public TempController(TempsRepository repo) {
        this.repo = repo;
    }

    @PostMapping("/data")
    public void temp(@RequestBody Payload temp, @RequestParam("location") String location) {
        log.info("New data for location {} -> {}", location, temp.toString());
        repo.addTemps(location, temp);
        repo.read(location);
    }

    @GetMapping("/data")
    public List<Payload> temp(@RequestParam("location") String location) {
        log.info("Get data by location {}", location);
        return repo.read(location);
    }

    @Deprecated
    @GetMapping("/dataa")
    public List<DataByLocation> temp() {
        log.info("Get data !");
        return repo.read();
    }

    @GetMapping("/data/locations")
    public List<String> getLocation() {
        log.info("Get location !");
        return repo.readLocation();
    }

    @PostConstruct
    public void postConstruct() {
        log.info("Version {}", buildProperties.getVersion());
    }

}
