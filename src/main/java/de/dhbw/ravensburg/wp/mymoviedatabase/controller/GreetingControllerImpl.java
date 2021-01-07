package de.dhbw.ravensburg.wp.mymoviedatabase.controller;

import de.dhbw.ravensburg.wp.mymoviedatabase.service.GreetingService;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingControllerImpl implements GreetingController {

    private GreetingService greetingService;

    public GreetingControllerImpl(GreetingService greetingService){
        this.greetingService = greetingService;
    }

    public String personalizeGreeting(String username, String language){
        //Falls kein username übergeben wird, wird Max Musterman als Username gesetzt
        if (null == username || username.isEmpty()){
            username="Max Mustermann";
        }
        return this.greetingService.personalizeGreeting(username, language);
    }

}
