package com.dh.catalogservice.repository;

import com.dh.catalogservice.model.SerieWS;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "serie-service")
public interface SerieRepository {
    @GetMapping("/series/{genre}")
    List<SerieWS> getSeriesByGenre(@PathVariable String genre);

    @PostMapping("/series/")
    SerieWS saveSerie(@RequestBody SerieWS serie);
}
