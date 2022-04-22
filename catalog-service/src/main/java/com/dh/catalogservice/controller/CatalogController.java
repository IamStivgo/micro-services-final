package com.dh.catalogservice.controller;

import com.dh.catalogservice.model.MovieWS;
import com.dh.catalogservice.model.SerieWS;
import com.dh.catalogservice.service.CatalogService;
import com.dh.catalogservice.model.CatalogWS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/catalog")
public class CatalogController {
	private CatalogService catalogService;

	@Autowired
	public CatalogController(CatalogService catalogService) {
		this.catalogService = catalogService;
	}

	@GetMapping("/{genre}")
	ResponseEntity<CatalogWS> getCatalogByGenre(@PathVariable String genre) {
		return catalogService.getCatalogByGenre(genre);
	}

	@PostMapping("/series")
	ResponseEntity<SerieWS> saveSerie(@RequestBody SerieWS serie){
		return catalogService.saveSerie(serie);
	}

	@PostMapping("/movies")
	ResponseEntity<MovieWS> saveMovie(@RequestBody MovieWS movie){
		return catalogService.saveMovie(movie);
	}
}
