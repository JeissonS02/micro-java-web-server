package co.edu.escuelaing.reflexionlab.framework;

import co.edu.escuelaing.reflexionlab.annotations.GetRoute;
import co.edu.escuelaing.reflexionlab.annotations.WebController;

import java.io.File;
import java.lang.reflect.Method;

public class ControllerScanner {

    public static Object scan(String basePackage) throws Exception {

        String path = "target/classes/" + basePackage.replace(".", "/");

        File folder = new File(path);

        Object controllerInstance = null;

        for(File file : folder.listFiles()){

            if(file.getName().endsWith(".class")){

                String className =
                        basePackage + "." + file.getName().replace(".class","");

                Class<?> clazz = Class.forName(className);

                if(clazz.isAnnotationPresent(WebController.class)){

                    controllerInstance = clazz.getDeclaredConstructor().newInstance();

                    for(Method m : clazz.getDeclaredMethods()){

                        if(m.isAnnotationPresent(GetRoute.class)){

                            GetRoute route = m.getAnnotation(GetRoute.class);

                            RouteTable.register(route.value(), controllerInstance, m);

                            System.out.println("Route loaded: " + route.value());
                        }
                    }
                }
            }
        }

        return controllerInstance;
    }
}