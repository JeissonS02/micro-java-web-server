package co.edu.escuelaing.reflexionlab.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface GetRoute {

    String value();

}