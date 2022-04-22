package com.dh.movieservice.controller;

import com.dh.movieservice.service.MovieService;
import com.dh.movieservice.model.Movie;
import com.dh.movieservice.service.queue.MovieSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
	private MovieService movieService;

	private MovieSender movieSender;

	@Autowired
	public MovieController(MovieService movieService, MovieSender movieSender) {
		this.movieService = movieService;
		this.movieSender = movieSender;
	}

	@GetMapping("/{genre}")
	public ResponseEntity<List<Movie>> getMovieByGenre(@PathVariable String genre) {
		return ResponseEntity.ok().body(movieService.getListByGenre(genre));
	}

	@PostMapping
	public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
		Movie movieCreate = movieService.save(movie);
		movieSender.send(movieCreate);
		return ResponseEntity.ok().body(movieCreate);
	}
}
