package de.dhbw.ravensburg.wp.mymoviedatabase.controller;

import java.util.List;

public interface MovieController {
    boolean addMovieTitle(String movieTitle);
    List<String> getMovieTitles(String pattern);
}
