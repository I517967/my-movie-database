package de.dhbw.ravensburg.wp.mymoviedatabase.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

public interface ThMovieController {
    @GetMapping(value = {"/", "index.html"})
    ModelAndView index();
}
