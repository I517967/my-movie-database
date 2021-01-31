package de.dhbw.ravensburg.wp.mymoviedatabase.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class GreetingServiceImplTest {

    @Autowired //bei den Tests: Fieldinjection
    private GreetingService greetingService; //brauchen die Bean, die wir testen (hier GreetingServie)
    // Ctr Alt O, bei Optimize imports schauen was unter Code

    @Test
    public void shouldSuccessfullyReturnGreeting(){
        String username = "Peter";
        String language = "de";
        String expection = "Hallo Peter, herzlich willkommen zu MyMovieDatabase";
        String result = this.greetingService.personalizeGreeting(username, language); //this. immer wenn Klassenattriute referenziert werden,
        //ermöglicht es dem Leser zu erkennen, der greetingService ist der da oben und nichts was ich hier in der Methode def. hab
        assertEquals(expection, result); //muss nicht assertEquals
    } //hier haben wir die Parameter statisch in der Methode definiert, hat den Nachteil, dass unser Code massiv verlängert würde, würde man hier noch einen 2. und 3. Test machen
    //den Platz spart man sich, indem man die ganzen Parameter in eine Zeile packt und die Methodensignatur dementsprechend anpasst
    //macht der Logik des Tests keinen Abbruch, ist einfach identlisch

    @ParameterizedTest
    @CsvSource({"Peter, de, 'Hallo Peter, herzlich willkommen zu MyMovieDatabase'",
            "James, en, 'Hello James, welcome to MyMovieDatabase'",
            "Chloe, fr, 'Salut Chloe, bienvenue à MyMovieDatabase'"})
    public void shouldSuccessfullyReturnGreetings(String username, String language, String expectation){ //expecting = die Rückgabe die ich erwarte
        String result = greetingService.personalizeGreeting(username, language);
        assertEquals(expectation, result);
    }

    //generell schauen, dass man den ganzen String testet, da wenn man sich nur ein zwei Parameter rauspickt, immer nur gegen den gleichen Fehler testet, macht man sich angreifbar gegebpber anderen Fehlern
}
