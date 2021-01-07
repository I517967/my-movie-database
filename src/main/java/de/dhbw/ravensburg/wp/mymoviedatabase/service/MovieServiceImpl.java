package de.dhbw.ravensburg.wp.mymoviedatabase.service;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService{

    private LinkedList<String> movieTitles;

    //Default Konstruktor, der eine leere Liste anlegt.
    public MovieServiceImpl(){
        this.movieTitles = new LinkedList<>();
    }

    //Fügt weitere Titel der Liste an, falls diese nicht bereits existieren
    //Im Fall, dass der Titel bereits existiert wird eine RuntimeException geworfen.
    public void addMovieTitle(String movieTitle){
        if (movieTitles.contains(movieTitle)){
            throw new RuntimeException("Title already exists");
        }
        this.movieTitles.add(movieTitle);
    }

    //Gibt alle Filmtitel zurück, welche den übergebenen Kriterium entsprechen.
    public List<String> getMovieTitles(String subString){
        List<String> result = new LinkedList<>();
        for (String title:this.movieTitles) {
            if (title.contains(subString)){
                result.add(title);
            }
        }
        return result;
    }

    public void removeMovieTitle(String movieTitle){
        this.movieTitles.remove(movieTitle);
    }

}
