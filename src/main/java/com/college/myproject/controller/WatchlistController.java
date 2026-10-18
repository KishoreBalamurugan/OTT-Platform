package com.college.myproject.controller;

import com.college.myproject.model.Watchlist;
import com.college.myproject.security.JwtUtil;
import com.college.myproject.service.WatchlistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/watchlist")
public class WatchlistController {

    private final JwtUtil jwtUtil;
    private final WatchlistService watchlistService;

    public WatchlistController(JwtUtil jwtUtil, WatchlistService watchlistService) {
        this.jwtUtil = jwtUtil;
        this.watchlistService = watchlistService;
    }

    @PostMapping("/add/{movieId}")
    public ResponseEntity<String> addToWatchlist(@RequestHeader("Authorization") String authHeader,
                                                 @PathVariable Long movieId) {
        String token = authHeader.substring(7);
        String email = jwtUtil.getEmailFromToken(token);

        String result = watchlistService.addMovieToWatchlist(email, movieId);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<?> getWatchlist(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        String email = jwtUtil.getEmailFromToken(token);

        List<Watchlist> watchlist = watchlistService.getWatchlistByEmail(email);
        if (watchlist == null) {
            return ResponseEntity.status(404).body("User not found");
        }
        return ResponseEntity.ok(watchlist);
    }
}