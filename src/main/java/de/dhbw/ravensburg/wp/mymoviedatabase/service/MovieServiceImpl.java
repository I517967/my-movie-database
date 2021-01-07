package de.dhbw.ravensburg.wp.mymoviedatabase.service;

import de.dhbw.ravensburg.wp.mymoviedatabase.model.Movie;
import de.dhbw.ravensburg.wp.mymoviedatabase.repository.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService{

    private MovieRepository movieRepository;


    //Default Konstruktor, der eine leere Liste anlegt.
    public MovieServiceImpl(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    //Fügt weitere Titel der Liste an, falls diese nicht bereits existieren
    //Im Fall, dass der Titel bereits existiert wird eine RuntimeException geworfen.
    public void addMovieTitle(String movieTitle){
        Movie tmp = new Movie();
        tmp.setTitle(movieTitle);
        this.movieRepository.save(tmp);
    }

    //Gibt alle Filmtitel zurück, welche den übergebenen Kriterium entsprechen.
    public List<String> getMovieTitles(String subString){
        List<String> result = new LinkedList<>();
        List<Movie> tmp = this.movieRepository.findByTitleContaining(subString);
        tmp.forEach(movie -> result.add(movie.getTitle()));
        return result;
    }

    public void removeMovieTitle(String movieTitle){
        List<Movie> tmp = this.movieRepository.findByTitleContaining("movieTitle");
        if(tmp.size() == 1){
            this.movieRepository.delete(tmp.get(0));
        }
    }

    public List<Movie> getAllMovies(){
        return this.movieRepository.findAll();
    }


    public void removeById(long id){
        this.movieRepository.deleteById(id);
    }

    public Movie addMovie(Movie movie){
        this.movieRepository.save(movie);
        return movie;
    }


    @Transactional
    public Movie updateMovie(Movie new_movie){
        Movie old_movie = this.movieRepository.findById(new_movie.getId()).get();
        old_movie.setTitle(new_movie.getTitle());
        old_movie.setDescription(new_movie.getDescription());
        old_movie.setCoverImgUrl(new_movie.getCoverImgUrl());
        old_movie.setDuration(new_movie.getDuration());
        old_movie.setImdbRating(new_movie.getImdbRating());
        old_movie.setPremiereDate(new_movie.getPremiereDate());
        //Wie lassen sich die Referenzen updaten?
        return old_movie;
    }


    //Im Transaktionskontext können LazyLoading Beziehungen aufgelöst werden
    @Transactional
    public List<String> getCastOfMovie(String movieTitle){
        List<String> result = new LinkedList<>();
        List<Movie> tmp = this.movieRepository.findByTitleContaining(movieTitle);
        if (tmp.size() == 1){
            tmp.get(0).getInvolvedCast().forEach(cast -> result.add(cast.getForename()));
        }
        return result;
    }


    //Aufgabe 4c: Was stimmt hier nicht?
    @Transactional
    public void changeMovieTitle(long id, String newMovieName){
        Movie tmp = this.movieRepository.findById(id).get();
        tmp.setTitle(newMovieName);
        if (!newMovieName.isEmpty()){
            this.movieRepository.save(tmp);
        }
    }

}
