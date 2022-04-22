package com.dh.serieservice.service.impl;

import com.dh.serieservice.model.Serie;
import com.dh.serieservice.repository.SerieRepository;
import com.dh.serieservice.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SerieServiceImpl implements SerieService {
    private SerieRepository serieRepository;

    @Autowired
    public SerieServiceImpl(SerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }

    @Override
    public List<Serie> getSeriesByGenre(String genre) {
        return serieRepository.findAllByGenre(genre.toUpperCase());
    }

    @Override
    public Serie save(Serie serie) {
        serie.setGenre(serie.getGenre().toUpperCase());
        return serieRepository.save(serie);
    }
}
