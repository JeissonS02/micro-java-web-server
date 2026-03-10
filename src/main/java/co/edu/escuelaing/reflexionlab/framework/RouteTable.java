package co.edu.escuelaing.reflexionlab.framework;

import java.lang.reflect.Method;
import java.util.HashMap;

public class RouteTable {

    public static class Route {
        public Object instance;
        public Method method;

        public Route(Object instance, Method method){
            this.instance = instance;
            this.method = method;
        }
    }

    private static HashMap<String, Route> routes = new HashMap<>();

    public static void register(String path, Object instance, Method method){
        routes.put(path, new Route(instance, method));
    }

    public static Route get(String path){
        return routes.get(path);
    }
}