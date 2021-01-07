package de.dhbw.ravensburg.wp.mymoviedatabase.service;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class GreetingServiceImpl implements GreetingService{

    @Override
    public String personalizeGreeting(String name, String language) {
        String greeting = "";

        switch (language){
            case "de": greeting = String.format("Hallo %s, herzlich willkommen zu MyMovieDatabase", name); break;
            case "en": greeting = String.format("Hello %s, wellcome to MyMovieDatabase", name); break;
            case "fr": greeting = String.format("Salut %s, bienvenue à MyMovieDatabase", name); break;
            default: greeting = String.format("Hola %s, bienvenido a MyMovieDatabase", name);
        }
        return greeting;
    }


}
