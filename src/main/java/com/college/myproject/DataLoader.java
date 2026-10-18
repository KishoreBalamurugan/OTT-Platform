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

        // Add movies only if they are not already present
        addMovieIfNotExists(
                "Inception",
                "Movie",
                "Sci-Fi",
                2010,
                "A thief who steals corporate secrets through dream-sharing technology.",
                "https://image.tmdb.org/t/p/w500/oYuLEt3zVCKq57qu2F8dT7NIa6f.jpg",
                4.8
        );

        addMovieIfNotExists(
                "Interstellar",
                "Movie",
                "Sci-Fi",
                2014,
                "A group of explorers travel through a wormhole in search of a new home.",
                "https://image.tmdb.org/t/p/w500/gEU2QniE6E77NI6lCU6MxlNBvIx.jpg",
                4.9
        );

        addMovieIfNotExists(
                "The Dark Knight",
                "Movie",
                "Action",
                2008,
                "Batman faces a criminal mastermind who plunges Gotham into chaos.",
                "https://image.tmdb.org/t/p/w500/qJ2tW6WMUDux911r6m7haRef0WH.jpg",
                4.9
        );

        addMovieIfNotExists(
                "The Matrix",
                "Movie",
                "Sci-Fi",
                1999,
                "A hacker discovers that reality is not what it seems.",
                "https://image.tmdb.org/t/p/w500/f89U3ADr1oiB1s9GkdPOEpXUk5H.jpg",
                4.7
        );

        addMovieIfNotExists(
                "Oppenheimer",
                "Movie",
                "Drama",
                2023,
                "The story of the scientist who led the development of the atomic bomb.",
                "https://image.tmdb.org/t/p/w500/8Gxv8gSFCU0XGDykEGv7zR1n2ua.jpg",
                4.8
        );

        addMovieIfNotExists(
                "Dune",
                "Movie",
                "Sci-Fi",
                2021,
                "A young nobleman becomes involved in a struggle for control of a desert planet.",
                "https://image.tmdb.org/t/p/w500/1pdfLvkbY9ohJlCjQH2CZjjYVvJ.jpg",
                4.7
        );

        addMovieIfNotExists(
                "Avengers: Endgame",
                "Movie",
                "Action",
                2019,
                "The Avengers attempt to undo the destruction caused by Thanos.",
                "https://image.tmdb.org/t/p/w500/or06FN3Dka5tukK1e9sl16pB3iy.jpg",
                4.8
        );

        addMovieIfNotExists(
                "John Wick",
                "Movie",
                "Action",
                2014,
                "An assassin returns to his violent past after a devastating loss.",
                "https://image.tmdb.org/t/p/w500/fZPSd91yGE9fCcCe6OoQr6E3Bev.jpg",
                4.6
        );

        addMovieIfNotExists(
                "Stranger Things",
                "Series",
                "Sci-Fi",
                2016,
                "A group of friends uncover supernatural mysteries in their town.",
                "https://image.tmdb.org/t/p/w500/uOOtwVbSr4QDjAGIifLDwpb2Pdl.jpg",
                4.7
        );

        addMovieIfNotExists(
                "Breaking Bad",
                "Series",
                "Drama",
                2008,
                "A chemistry teacher turns to manufacturing illegal drugs.",
                "https://image.tmdb.org/t/p/w500/ggFHVNu6YYI5L9pCfOacjizRGt.jpg",
                4.9
        );

        addMovieIfNotExists(
                "The Boys",
                "Series",
                "Action",
                2019,
                "A group of vigilantes takes on corrupt superheroes.",
                "https://www.themoviedb.org/tv/76479-the-boys/images/posters",
                4.6
        );

        addMovieIfNotExists(
                "Peaky Blinders",
                "Series",
                "Drama",
                2013,
                "A crime family builds its influence in post-war Birmingham.",
                "https://image.tmdb.org/t/p/w500/vUUqzWa2LnHIVqkaKVlVGkVcZIW.jpg",
                4.8
        );

        addMovieIfNotExists(
                "Dark",
                "Series",
                "Mystery",
                2017,
                "Four families uncover a mysterious time-travel conspiracy.",
                "https://image.tmdb.org/t/p/w500/stTEycfG9928HYGEIS3m5i7R4C6.jpg",
                4.7
        );

        addMovieIfNotExists(
                "Sherlock",
                "Series",
                "Mystery",
                2010,
                "A brilliant detective and his companion solve unusual crimes in London.",
                "https://image.tmdb.org/t/p/w500/7WTsnHkbA0FaG6R9twfFde0I9HL.jpg",
                4.8
        );

        addMovieIfNotExists(
                "Money Heist",
                "Series",
                "Crime",
                2017,
                "A criminal mastermind leads a group through an ambitious heist.",
                "https://image.tmdb.org/t/p/w500/reEMJA1uzscCbkpeRJeTT2bjqUp.jpg",
                4.7
        );


        // Add test user only if users table is empty
        if (userRepository.count() == 0) {

            User kishore = new User(
                    "Kishore",
                    "kishore@gmail.com",
                    "$2a$10$GTei/TcfcLRymX7O1sRQxubbmPg3FvY8ZsTeJze8WbefcHb2jYAji"
            );

            userRepository.save(kishore);
        }
    }


    // Helper method to prevent duplicate movies
    private void addMovieIfNotExists(
            String name,
            String type,
            String genre,
            int year,
            String description,
            String posterUrl,
            double rating) {

        if (movieRepository.findByName(name).isEmpty()) {

            Movie movie = new Movie(
                    name,
                    type,
                    genre,
                    year,
                    description,
                    posterUrl,
                    rating
            );

            movieRepository.save(movie);

            System.out.println("Added movie: " + name);
        }
    }
}