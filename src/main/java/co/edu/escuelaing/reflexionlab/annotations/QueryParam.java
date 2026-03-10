package co.edu.escuelaing.reflexionlab.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface QueryParam {

    String value();
    String defaultValue() default "";

}