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

    @Autowired // wir testen den movieController, deshalb holen wir uns den her mit dem @Autowired
    MovieController movieController;

    //Durch MockBean wird automatisch ein Mock für die Klasse erzeugt
    @MockBean //Definition der MockBean -> sind im Controller, der hat zwangsweise immer Aufrufe zu dem Service,
            //also die Präsentationsschicht hat immer Aufrufe zu der Logikschicht, müssen diese irgendwie austauschen, machen wir indem wie @mockbean über den movieservice schreiben
            //jetzt wird eine hohle  implementierung für das movieService Interface verwendet
    MovieService movieService;

    @Test
    public void shouldReturnEmptyMovieList(){
        //Reguläre When-Then Syntax, da keine void Methode aufgerufen wird
        //machen gemäß der Stubdefinition eine Beschreibung des Antwortverhaltens, wie sich der MovieService bei einem Aufruf auf diesen MovieService verhalten sollte
        //rufen erst den movieService, dann die Methode auf und übergeben ihr irgendeinen String, dann gebe eine leere Liste zurück, neue Instanziierung einer LinkedList
        //damit ist der Stub definiert, haben unser Antwortverhalen angepasst, weil davor würden wir einfach nur null zrk bekommen
        when(movieService.getMovieTitles(anyString())).thenReturn(new LinkedList<>());
        //rufen den movieController auf, die Methode mit dem Input(auf den kommt es beim Controller nicht so sehr an)
        List<String> result = this.movieController.getMovieTitles("man");
        //Sicherstellen/überprüfen dass addMovieTitle aufgerufen wird (gemäß dem Mock), d.h dass der Controller den Service aufgerufen hat
        // wir verifizieren , dass auf dem MovieSErvice die Methode getMovieTitles mit dem Parameter man auferufen wurde
        verify(movieService).getMovieTitles("man");
        //Sicherstellen, dass der Controller eine leere Liste zurückgibt,
        //sicherstellen, dass mit dem Parameter richtig weiter gerechnet wird, hier: dass wieder eine leere Liste zrk gegeben wird
        //haben oben def., dass der Service eine leere Liste zurückgeben soll und hier stellen wir sicher, dass der Controller dem Aufrufer auch tatsächlich eine leere Listse zrk gibt
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
