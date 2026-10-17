package com.college.myproject;

import com.college.myproject.model.Movie;
import com.college.myproject.repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final MovieRepository movieRepository;

    public DataLoader(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void run(String... args) {

        if (movieRepository.count() == 0) {

            movieRepository.save(new Movie(
                    "Inception",
                    "Movie",
                    "Sci-Fi",
                    2010,
                    "A thief who steals corporate secrets through dream-sharing technology.",
                    "https://image.tmdb.org/t/p/w500/oYuLEt3zVCKq57qu2F8dT7NIa6f.jpg"
            ));

            movieRepository.save(new Movie(
                    "Interstellar",
                    "Movie",
                    "Sci-Fi",
                    2014,
                    "A group of explorers travel through a wormhole in search of a new home.",
                    "https://image.tmdb.org/t/p/w500/gEU2QniE6E77NI6lCU6MxlNBvIx.jpg"
            ));

            movieRepository.save(new Movie(
                    "The Dark Knight",
                    "Movie",
                    "Action",
                    2008,
                    "Batman faces a criminal mastermind who plunges Gotham into chaos.",
                    "https://image.tmdb.org/t/p/w500/qJ2tW6WMUDux911r6m7haRef0WH.jpg"
            ));

            movieRepository.save(new Movie(
                    "Stranger Things",
                    "Series",
                    "Sci-Fi",
                    2016,
                    "A group of friends uncover supernatural mysteries in their town.",
                    "https://image.tmdb.org/t/p/w500/uOOtwVbSr4QDjAGIifLDwpb2Pdl.jpg"
            ));

            movieRepository.save(new Movie(
                    "Breaking Bad",
                    "Series",
                    "Drama",
                    2008,
                    "A chemistry teacher turns to manufacturing illegal drugs.",
                    "https://image.tmdb.org/t/p/w500/ztkUQFLlC4CCbHbF7Y2d6J9Q9Yw.jpg"
            ));
        }
    }
}