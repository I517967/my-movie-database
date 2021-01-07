package de.dhbw.ravensburg.wp.mymoviedatabase.service;

import de.dhbw.ravensburg.wp.mymoviedatabase.dto.MovieDTO;
import de.dhbw.ravensburg.wp.mymoviedatabase.mapper.MovieMapper;
import de.dhbw.ravensburg.wp.mymoviedatabase.model.Cast;
import de.dhbw.ravensburg.wp.mymoviedatabase.model.Movie;
import de.dhbw.ravensburg.wp.mymoviedatabase.repository.CastRepository;
import de.dhbw.ravensburg.wp.mymoviedatabase.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
@Slf4j
public class MovieServiceImpl implements MovieService{

    private MovieRepository movieRepository;
    private MovieMapper movieMapper;
    private CastRepository castRepository;


    //Default Konstruktor, der eine leere Liste anlegt.
    public MovieServiceImpl(MovieRepository movieRepository, CastRepository castRepository,
                            MovieMapper movieMapper){
        this.movieRepository = movieRepository;
        this.castRepository = castRepository;
        this.movieMapper = movieMapper;
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

    public List<MovieDTO> getAllMovies(){
        return movieMapper.moviesToMovieDTOs(movieRepository.findAll());
    }

    public MovieDTO getMovieById(Long id){return this.movieMapper.movieToMovieDTO(movieRepository.findById(id).get());}


    public void removeMovieById(long id){
        this.movieRepository.deleteById(id);
    }

    public MovieDTO addMovie(MovieDTO movieDTO){
        Movie tmp = this.movieMapper.movieDTOToMovie(movieDTO);
        this.movieRepository.save(tmp);
        return this.movieMapper.movieToMovieDTO(tmp);
    }


    @Transactional
    public MovieDTO updateMovie(MovieDTO newMovie) throws EntityNotFoundException{
        Movie tmp = this.movieMapper.movieDTOToMovie(newMovie);

        List<Cast> newCast = new ArrayList<>();
        newMovie.getCastIds().forEach(castId -> newCast.add(this.castRepository.findById(castId).get()));
        tmp.setInvolvedCast(newCast);

        this.movieRepository.save(tmp);
        return this.movieMapper.movieToMovieDTO(tmp);
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
