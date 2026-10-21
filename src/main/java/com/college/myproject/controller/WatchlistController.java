package com.college.myproject.controller;

import com.college.myproject.model.Watchlist;
import com.college.myproject.security.JwtUtil;
import com.college.myproject.service.WatchlistService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> addToWatchlist(@RequestHeader(value = "Authorization", required = false) String authHeader,
                                            @PathVariable Long movieId) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid authorization token");
        }
        String token = authHeader.substring(7);
        if (!jwtUtil.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token");
        }
        String email = jwtUtil.getEmailFromToken(token);

        String result = watchlistService.addMovieToWatchlist(email, movieId);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/remove/{movieId}")
    public ResponseEntity<?> removeFromWatchlist(@RequestHeader(value = "Authorization", required = false) String authHeader,
                                                 @PathVariable Long movieId) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid authorization token");
        }
        String token = authHeader.substring(7);
        if (!jwtUtil.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token");
        }
        String email = jwtUtil.getEmailFromToken(token);

        String result = watchlistService.removeMovieFromWatchlist(email, movieId);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<?> getWatchlist(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid authorization token");
        }
        String token = authHeader.substring(7);
        if (!jwtUtil.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token");
        }
        String email = jwtUtil.getEmailFromToken(token);
        List<Watchlist> watchlist = watchlistService.getWatchlistByEmail(email);
        return ResponseEntity.ok(watchlist);
    }
}