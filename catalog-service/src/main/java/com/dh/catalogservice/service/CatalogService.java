package com.dh.catalogservice.service;

import com.dh.catalogservice.model.CatalogWS;
import com.dh.catalogservice.model.MovieWS;
import com.dh.catalogservice.model.SerieWS;
import org.springframework.http.ResponseEntity;

public interface CatalogService {

    ResponseEntity<CatalogWS> getCatalogByGenre(String genre);

    ResponseEntity<SerieWS> saveSerie(SerieWS serie);

    ResponseEntity<MovieWS> saveMovie(MovieWS movie);

    void saveSerieCatalog(SerieWS serie);

    void saveMovieCatalog(MovieWS movie);
}
