package de.dhbw.ravensburg.wp.mymoviedatabase.controller;

import de.dhbw.ravensburg.wp.mymoviedatabase.service.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
public class MovieControllerImplTest {

    @Autowired
    MovieController movieController;

    //Durch MockBean wird automatisch ein Mock für die Klasse erzeugt
    @MockBean
    MovieService movieService;

    @Test
    public void shouldReturnEmptyMovieList(){
        //Reguläre When-Then Syntax, da keine void Methode aufgerufen wird
        when(movieService.getMovieTitles(anyString())).thenReturn(new LinkedList<>());
        List<String> result = this.movieController.getMovieTitles("man");
        //Sicherstellen dass addMovieTitle aufgerufen wird
        verify(movieService).getMovieTitles("man");
        //Sicherstellen, dass der Controller eine leere Liste zurückgibt
        assertTrue(result.isEmpty());
    }

    @Test
    public void shouldReturnFullMovieList(){
        List<String> movieList = Arrays.asList("Superman", "Spiderman");
        //Reguläre When-Then Syntax, da keine void Methode aufgerufen wird
        when(movieService.getMovieTitles(anyString())).thenReturn(movieList);
        List<String> result = this.movieController.getMovieTitles("man");
        //Sicherstellen dass addMovieTitle aufgerufen wird
        verify(movieService).getMovieTitles(anyString());
        //Sicherstellen, dass der Controller eine Liste mit den zwei bekannten Elementen zurückgibt
        assertTrue(result.size() == 2);
        assertTrue(result.containsAll(movieList));
    }

    @Test
    public void shouldSuccessfullyAddMovieTitle(){
        //Achtung bei void Methoden umgedrehte Reihenfolge Do-When statt When-Then
        doNothing().when(movieService).addMovieTitle(anyString());
        boolean result = this.movieController.addMovieTitle("Interstellar");
        //Sicherstellen dass addMovieTitle aufgerufen wird
        verify(movieService).addMovieTitle("Interstellar");
        //Sicherstellen, dass der Controller true zurückgibt, da der Titel neu ist
        assertTrue(result);
    }

    @Test
    public void shouldFailAddMovieTitle(){
        //Achtung bei void Methoden umgedrehte Reihenfolge Do-When statt When-Then
        //Hier wird eine Exception geworfen.
        doThrow(new RuntimeException("Title already exists")).when(movieService).addMovieTitle("Interstellar");
        boolean result = this.movieController.addMovieTitle("Interstellar");
        //Sicherstellen dass addMovieTitle aufgerufen wird
        verify(movieService).addMovieTitle("Interstellar");
        //Sicherstellen, dass der Controller false in Folge der Exception zurückgibt
        assertFalse(result);
    }

}
