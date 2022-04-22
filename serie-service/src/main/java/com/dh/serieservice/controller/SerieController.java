package com.dh.serieservice.controller;

import com.dh.serieservice.model.Serie;
import com.dh.serieservice.service.SerieService;
import com.dh.serieservice.service.queue.SerieSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/series")
public class SerieController {
    private SerieService serieService;
    private SerieSender serieSender;

    @Autowired
    public SerieController(SerieService serieService, SerieSender serieSender) {
        this.serieService = serieService;
        this.serieSender = serieSender;
    }

    @GetMapping("/{genre}")
    public ResponseEntity<List<Serie>> getSeriesByGenre(@PathVariable String genre){
        return ResponseEntity.ok().body(serieService.getSeriesByGenre(genre));
    }

    @PostMapping
    public ResponseEntity<Serie> saveSerie(@RequestBody Serie serie){
        Serie serieCreated = serieService.save(serie);
        serieSender.send(serie);
        return ResponseEntity.ok().body(serieCreated);
    }
}
