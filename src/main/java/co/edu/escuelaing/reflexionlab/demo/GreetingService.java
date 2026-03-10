package co.edu.escuelaing.reflexionlab.demo;

import co.edu.escuelaing.reflexionlab.annotations.*;

@WebController
public class GreetingService {

    @GetRoute("/greeting")
    public String greeting(
            @QueryParam(value="name", defaultValue="World") String name){

        return "Hola " + name;

    }
}