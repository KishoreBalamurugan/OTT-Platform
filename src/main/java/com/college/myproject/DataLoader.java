package com.college.myproject;

import com.college.myproject.model.Movie;
import com.college.myproject.model.User;
import com.college.myproject.repository.MovieRepository;
import com.college.myproject.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    public DataLoader(MovieRepository movieRepository, UserRepository userRepository) {
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {

        // 1. Seed Movies with ratings if the table is empty
        if (movieRepository.count() == 0) {
            movieRepository.save(new Movie(
                    "Inception",
                    "Movie",
                    "Sci-Fi",
                    2010,
                    "A thief who steals corporate secrets through dream-sharing technology.",
                    "https://image.tmdb.org/t/p/w500/oYuLEt3zVCKq57qu2F8dT7NIa6f.jpg",
                    4.8
            ));

            movieRepository.save(new Movie(
                    "Interstellar",
                    "Movie",
                    "Sci-Fi",
                    2014,
                    "A group of explorers travel through a wormhole in search of a new home.",
                    "https://image.tmdb.org/t/p/w500/gEU2QniE6E77NI6lCU6MxlNBvIx.jpg",
                    4.9
            ));

            movieRepository.save(new Movie(
                    "The Dark Knight",
                    "Movie",
                    "Action",
                    2008,
                    "Batman faces a criminal mastermind who plunges Gotham into chaos.",
                    "https://image.tmdb.org/t/p/w500/qJ2tW6WMUDux911r6m7haRef0WH.jpg",
                    4.9
            ));

            movieRepository.save(new Movie(
                    "Stranger Things",
                    "Series",
                    "Sci-Fi",
                    2016,
                    "A group of friends uncover supernatural mysteries in their town.",
                    "https://image.tmdb.org/t/p/w500/uOOtwVbSr4QDjAGIifLDwpb2Pdl.jpg",
                    4.7
            ));

            movieRepository.save(new Movie(
                    "Breaking Bad",
                    "Series",
                    "Drama",
                    2008,
                    "A chemistry teacher turns to manufacturing illegal drugs.",
                    "https://image.tmdb.org/t/p/w500/ztkUQFLlC4CCbHbF7Y2d6J9Q9Yw.jpg",
                    4.9
            ));
        }

        // 2. Seed Test User (Kishore) if the users table is empty
        if (userRepository.count() == 0) {
            User kishore = new User(
                    "Kishore",
                    "kishore@gmail.com",
                    "$2a$10$GTei/TcfcLRymX7O1sRQxubbmPg3FvY8ZsTeJze8WbefcHb2jYAji" // pre-hashed password for "1234"
            );
            userRepository.save(kishore);
        }
    }
}