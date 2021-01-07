package de.dhbw.ravensburg.wp.mymoviedatabase.service;

import de.dhbw.ravensburg.wp.mymoviedatabase.dto.MovieDTO;

import java.util.List;

public interface MovieService {
    List<MovieDTO> getAllMovies();
    MovieDTO getMovieById(Long id);
    void removeMovieById(long id);
    MovieDTO addMovie(MovieDTO movie);
    MovieDTO updateMovie(MovieDTO movie);
}
