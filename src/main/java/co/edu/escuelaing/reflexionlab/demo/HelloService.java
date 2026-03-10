package co.edu.escuelaing.reflexionlab.demo;

import co.edu.escuelaing.reflexionlab.annotations.GetRoute;
import co.edu.escuelaing.reflexionlab.annotations.WebController;

@WebController
public class HelloService {

    @GetRoute("/")
    public String index(){
        return "MicroServer running!";
    }

    @GetRoute("/hello")
    public String hello(){
        return "Hola desde el framework!";
    }
}