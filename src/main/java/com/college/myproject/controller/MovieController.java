package com.college.myproject.controller;

import com.college.myproject.model.Movie;
import com.college.myproject.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

//Controller for handling movie and series catalog requests.

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    //Retrieve a filtered list of movies or series based on type, genre, or search keyword.

    @GetMapping
    public ResponseEntity<List<Movie>> getMovies(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String search) {

        List<Movie> movies = movieService.getMovies(type, genre, search);
        return ResponseEntity.ok(movies);
    }

    //Retrieve a specific movie by its unique ID. Throws 404 if not found.

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        Movie movie = movieService.getMovieById(id);
        return ResponseEntity.ok(movie);
    }
}