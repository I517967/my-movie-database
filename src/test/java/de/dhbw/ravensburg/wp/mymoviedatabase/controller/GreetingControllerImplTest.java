package de.dhbw.ravensburg.wp.mymoviedatabase.controller;

import de.dhbw.ravensburg.wp.mymoviedatabase.service.GreetingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
public class GreetingControllerImplTest {

    @Autowired //sind im Controller müssen uns diesem herleiten
    GreetingController greetingController;

    //beim Controller haben wir die Abhängigkeit zum Service, daher kommt die TestDouble Thematik ins Spiel
    @MockBean //der Mock den wir uns bauen wollen ist jetzt der greetingService, erschaffen Double für ihn
    GreetingService greetingService;

    @Test
    public void shouldSuccessfullyReturnGreetingNormal(){
        //definieren, wie dieses Antwortverhalten aussehen soll
        //könnten Peter und de reinschreiben, wenn wir es generisch haben wollen können wir mit any alles abdecken
        when(greetingService.personalizeGreeting(any(), any())).thenReturn("Hallo Peter, herzlich willkommen zu MyMovieDatabase"); //holen uns den String rein, den wir erwarten würden
        //das heißt aus der GreetingServiceImplTest, unserem bisherigen Test holen wir uns den expect String so sollte das Antwortverhalten sein,
        // könnte auch nur Hallo zrk. geben, hat hier keine fachliche Funktionalität
        String result = this.greetingController.personalizeGreeting("Peter", "de"); //würde so immer erfolgreich ausgeführt
        // jetzt fehlt noch der ganze Mockteil, waren hier im Stub und haben uns das Antwortverhalten vorgegeben, haben geschaut dass der Controller das richtig ausgibt, es fehlt noch der verify-Teil
        verify(greetingService).personalizeGreeting(any(),any()); //ist beim greetingService ein Aufruf eingegangen namens personalizeGreeting mit irgendeinem Parameter
        assertTrue(result.equals("Hallo Peter, herzlich willkommen zu MyMovieDatabase"));
    }

    @Test
    public void shouldSuccessfullyReturnGreetingNoUsernameJustLanguage(){
        //könnten theoretisch die gleiche when-Syntax verwenden, weil wir haben ja any() any() def., rüberkopieren, dann Import oben löschen und wenn man in einer Liste mehrere optionen hat, mockito auswählen
        //oder when-Zeile stur zu ende schreiben und die roten Markierungen riskieren
        //theoretisch würde mit leerem username Max mustemann zek kommen, wäre fachlich korrekt, wir weichen hier mit when aber von dem regulären Verhalten ab,
        //wir sagen, egal was in any() reinkommt wir geben hier immer den hallo Peter Rüchgabestring zurück, ob der passt fahclich oder nicht ist völlig egal
        when(greetingService.personalizeGreeting(any(), any())).thenReturn("Hallo Peter, herzlich willkommen zu MyMovieDatabase");
        String result = this.greetingController.personalizeGreeting("", "de");
        //wenn man überprüfen will ob die If-condition mit Max Mustermann zieht, einfach das verify anpassen dass MM derinsteht
        verify(greetingService).personalizeGreeting(any(),any());

        //hier können wir nun testen, die beiden Strings MÜSSEN identisch sein, weil das hier ist wie wir den Stub definieren wie dieses Antwortverhalten aussehen soll
        //unabhängig davon wie oben die Parameter gesetzt sind, da wir anyany haben greift die immer, unabhängig davon wie konkret der Username in dem Fall heißt (wenn wie Marc, de oben eingeben dann nicht)
        //mit dem Rückgabeparameter wird in unserem Bsp nicht weiterearbeitet, weil einfach 1:1 der Rückgabeparameter von dem Service zrkgegeben wird an den Nutzer
        assertTrue(result.equals("Hallo Peter, herzlich willkommen zu MyMovieDatabase"));
    }
}
//man muss ein Assert im Test haben sonst läuft er immer korrekt durch
//beim Service testet man die Fachlichkeit, beim Controller die Darstellung
