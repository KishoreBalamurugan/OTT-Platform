package com.college.myproject.repository;

import com.college.myproject.model.Movie;
import com.college.myproject.model.User;
import com.college.myproject.model.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WatchlistRepository extends JpaRepository<Watchlist, Long> {
    List<Watchlist> findByUser(User user);
    Optional<Watchlist> findByUserAndMovie(User user, Movie movie);
}