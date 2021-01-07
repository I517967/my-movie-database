package de.dhbw.ravensburg.wp.mymoviedatabase.service;

import java.util.List;

public interface MovieService {
    void addMovieTitle(String movieTitle);
    void removeMovieTitle(String movieTitle);
    List<String> getMovieTitles(String subString);
}
