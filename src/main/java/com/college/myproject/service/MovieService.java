package com.college.myproject.service;

import com.college.myproject.model.Movie;
import com.college.myproject.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getMovies(String type, String genre, String search) {

        List<Movie> movies = movieRepository.findAll();
        List<Movie> result = new ArrayList<>();

        for (Movie movie : movies) {

            boolean typeMatches = type == null ||
                    movie.getType().equalsIgnoreCase(type);

            boolean genreMatches = genre == null ||
                    movie.getGenre().equalsIgnoreCase(genre);

            boolean searchMatches = search == null ||
                    movie.getName().toLowerCase().contains(search.toLowerCase());

            if (typeMatches && genreMatches && searchMatches) {
                result.add(movie);
            }
        }

        return result;
    }

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElse(null);
    }
}