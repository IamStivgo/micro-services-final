package com.dh.catalogservice.service.impl;

import com.dh.catalogservice.model.SerieWS;
import com.dh.catalogservice.repository.CatalogRepository;
import com.dh.catalogservice.repository.SerieRepository;
import com.dh.catalogservice.service.CatalogService;
import com.dh.catalogservice.model.CatalogWS;
import com.dh.catalogservice.model.MovieWS;
import com.dh.catalogservice.repository.MovieRepository;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService {
	private MovieRepository movieRepository;
	private SerieRepository serieRepository;

	private CatalogRepository catalogRepository;

	@Autowired
	public CatalogServiceImpl(MovieRepository movieRepository, SerieRepository serieRepository, CatalogRepository catalogRepository) {
		this.movieRepository = movieRepository;
		this.serieRepository = serieRepository;
		this.catalogRepository = catalogRepository;
	}

	@Override
	public ResponseEntity<CatalogWS> getCatalogByGenre(String genre) {
		//List<MovieWS> movies = this.movieRepository.getMoviesByGenre(genre);
		//List<SerieWS> series = this.serieRepository.getSeriesByGenre(genre);
		//return new ResponseEntity<CatalogWS>(CatalogWS.builder().genre(genre).movies(movies).series(series).build(), HttpStatus.OK);
		return new ResponseEntity<CatalogWS>(catalogRepository.findByGenre(genre.toUpperCase()), HttpStatus.OK);
	}

	@Override
	@CircuitBreaker(name = "series", fallbackMethod = "dontSaveSerie")
	@Retry(name = "series")
	public ResponseEntity<SerieWS> saveSerie(SerieWS serie) {
		return new ResponseEntity<>(this.serieRepository.saveSerie(serie), HttpStatus.CREATED);
	}

	private ResponseEntity<SerieWS> dontSaveSerie(CallNotPermittedException exception) {
		return new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE);
	}

	@Override
	@CircuitBreaker(name = "movies", fallbackMethod = "dontSaveMovie")
	@Retry(name = "movies")
	public ResponseEntity<MovieWS> saveMovie(MovieWS movie) {
		return new ResponseEntity<>(this.movieRepository.saveMovie(movie), HttpStatus.CREATED);
	}

	private ResponseEntity<MovieWS> dontSaveMovie(CallNotPermittedException exception) {
		return new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE);
	}

	@Override
	public void saveSerieCatalog(SerieWS serie) {
		String genre = serie.getGenre();
		CatalogWS catalog = catalogRepository.findByGenre(genre);
		if (catalog == null){
			catalog = new CatalogWS(genre);
		}
		List<SerieWS> series = catalog.getSeries();
		if(series == null){
			catalog.setSeries(Collections.singletonList(serie));
		}else{
			catalog.getSeries().add(serie);
		}
		catalogRepository.save(catalog);
	}

	@Override
	public void saveMovieCatalog(MovieWS movie) {
		String genre = movie.getGenre().toUpperCase();
		CatalogWS catalog = catalogRepository.findByGenre(genre);
		if (catalog == null){
			catalog = new CatalogWS(genre);
		}
		List<MovieWS> movies = catalog.getMovies();
		if(movies == null){
			catalog.setMovies(Collections.singletonList(movie));
		}else{
			catalog.getMovies().add(movie);
		}
		catalogRepository.save(catalog);
	}
}
