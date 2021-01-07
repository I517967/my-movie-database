package de.dhbw.ravensburg.wp.mymoviedatabase.controller;

import de.dhbw.ravensburg.wp.mymoviedatabase.dto.MovieDTO;
import de.dhbw.ravensburg.wp.mymoviedatabase.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
public class ThMovieControllerImpl implements ThMovieController {

    MovieService movieService;

    public ThMovieControllerImpl(MovieService movieService){
        this.movieService = movieService;
    }

    @Override
    @GetMapping(value = {"/", "index.html"})
    public ModelAndView index(){
        List<MovieDTO> movies = this.movieService.getAllMovies();

        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("data", movies);
        return modelAndView;
    }

}
