package de.dhbw.ravensburg.wp.mymoviedatabase.controller;

import de.dhbw.ravensburg.wp.mymoviedatabase.service.MovieService;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class MovieControllerImpl implements MovieController {

    private MovieService movieService;

    public MovieControllerImpl (MovieService movieService){
        this.movieService = movieService;
    }

    public boolean addMovieTitle(String movieTitle){
        // Im Falle dass eine RuntimeException geworfen wird,
        // weil der Titel bereits besteht wird diese gefangen und false zurückgegeben
        try {
            this.movieService.addMovieTitle(movieTitle);
        }catch (RuntimeException exp) {
            return false;
        }
        return true;
    }

    public List<String> getMovieTitles(String pattern){
        return this.movieService.getMovieTitles(pattern);
    }


}
