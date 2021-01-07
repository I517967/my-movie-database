package de.dhbw.ravensburg.wp.mymoviedatabase.controller;

import de.dhbw.ravensburg.wp.mymoviedatabase.dto.MovieDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface MovieController {
    @GetMapping
    List<MovieDTO> getAllMovies();

    @GetMapping
    MovieDTO getMovie(@PathVariable("movieId") Long movieId);

    @DeleteMapping("/{movieId}")
    void deleteMovieById(@PathVariable("movieId") Long movieId);

    @PostMapping
    MovieDTO addMovie(@RequestBody MovieDTO movieDTO);

    @PutMapping("/{movieId}")
    MovieDTO updateMovie(@PathVariable("movieId") Long movieId, @RequestBody MovieDTO movieDTO);
}
