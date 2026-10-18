package com.college.myproject.service;

import com.college.myproject.model.Movie;
import com.college.myproject.model.User;
import com.college.myproject.model.Watchlist;
import com.college.myproject.repository.MovieRepository;
import com.college.myproject.repository.UserRepository;
import com.college.myproject.repository.WatchlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WatchlistService {

    private final WatchlistRepository watchlistRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    public WatchlistService(WatchlistRepository watchlistRepository,
                            UserRepository userRepository,
                            MovieRepository movieRepository) {
        this.watchlistRepository = watchlistRepository;
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
    }

    public String addMovieToWatchlist(String email, Long movieId) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) return "User not found";

        Movie movie = movieRepository.findById(movieId).orElse(null);
        if (movie == null) return "Movie not found";

        // Check if movie is already in user's watchlist
        Optional<Watchlist> existing = watchlistRepository.findByUserAndMovie(user, movie);
        if (existing.isPresent()) {
            return "Movie is already in your watchlist!";
        }

        Watchlist watchlistItem = new Watchlist(user, movie);
        watchlistRepository.save(watchlistItem);
        return "Movie added to watchlist successfully!";
    }

    public List<Watchlist> getWatchlistByEmail(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) return null;
        return watchlistRepository.findByUser(user);
    }
}