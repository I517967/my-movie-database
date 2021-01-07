package de.dhbw.ravensburg.wp.mymoviedatabase.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class MovieServiceImplTest {

    @Autowired
    private MovieService movieService;

    //Anlegen der Filmtitel als Klassenvariable um Testfehler aufgrund
    // der Rechtschreibung zu vermeiden
    private String interstellar = "Interstellar";
    private String titanik = "Titanik";
    private String spiderman = "Spiderman";

    //Vorbereitung der MovieTitle "Datenbank", vor jedem Aufruf, sodass auch Titel
    // verändert oder gelöscht werden könnten.
    @BeforeEach
    public void addMovieTitles(){
        movieService.addMovieTitle(interstellar);
        movieService.addMovieTitle(titanik);
        movieService.addMovieTitle(spiderman);
    }

    @AfterEach
    public void removeMovieTitles(){
        movieService.removeMovieTitle(interstellar);
        movieService.removeMovieTitle(titanik);
        movieService.removeMovieTitle(spiderman);
    }

    @Test
    public void shouldSuccessfullyAddMovieTitle(){
        //Aufruf der Servicemethode basierend auf der angelegten Filmliste
        List<String> result = movieService.getMovieTitles("man");
        //Überprüfung des Umfangs und des Inhalts der zurückgegebenen Liste
        assertTrue(result.size() == 1);
        assertTrue(result.contains(this.spiderman));
    }

    @Test
    public void shouldFailAddMovieTitle(){
        String interstellar2 = interstellar;
        //Überprüfung dass eine RuntimeException durch die Methode geworfen wird
        assertThrows(RuntimeException.class, () -> {
            movieService.addMovieTitle(interstellar2);
        });
    }

    @Test
    public void shouldFailAddMovieTitle2(){
        String interstellar2 = interstellar;
        //Speicherung der geworfenen Exception
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            movieService.addMovieTitle(interstellar2);
        });
        //Überprüfung der Fehlernachricht der geworfenen Exception
        assertTrue(thrown.getMessage().equals("Title already exists"));
    }

}
