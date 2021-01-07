package de.dhbw.ravensburg.wp.mymoviedatabase.controller;

import de.dhbw.ravensburg.wp.mymoviedatabase.model.Movie;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface MovieController {
    @GetMapping
    List<Movie> getAllMovies();

    @DeleteMapping("/{movieId}")
    void deleteMovie(@PathVariable("movieId") Long movieId);

    @PostMapping
    Movie addMovie(@RequestBody Movie movie);

    @PutMapping("/{movieId}")
    Movie updateMovie(@PathVariable("movieId") Long movieId, @RequestBody Movie movie);
}
