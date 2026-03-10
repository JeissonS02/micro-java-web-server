package co.edu.escuelaing.reflexionlab.demo;

import co.edu.escuelaing.reflexionlab.annotations.*;

@WebController
public class MathService {

    @GetRoute("/pi")
    public String pi() {
        return String.valueOf(Math.PI);
    }

    @GetRoute("/square")
    public String square(@QueryParam(value="n", defaultValue="0") String n) {

        int num = Integer.parseInt(n);

        return String.valueOf(num * num);
    }
}