package com.amouchere.temperaturemonitoring.controller;

import com.amouchere.temperaturemonitoring.domain.Temp;
import com.amouchere.temperaturemonitoring.domain.TempsByLocation;
import com.amouchere.temperaturemonitoring.repository.TempsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.awt.geom.AreaOp;

import java.util.List;

@RestController
@RequestMapping("api")
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class TempController {

    @Autowired
    public TempController(TempsRepository repo) {
        this.repo = repo;
    }

    private TempsRepository repo;

    @PostMapping("/temps")
    public void temp(@RequestBody Temp temp) {
        log.info(" ! new temp {}", temp.toString());
        repo.addTemps(temp);
        repo.read();
    }

    @GetMapping("/temps")
    public List<TempsByLocation> temp() {
        log.info("Get temps !");
        return repo.read();

    }

}
